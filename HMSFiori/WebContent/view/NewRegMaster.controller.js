sap.ui.controller("test.my.fiori.view.NewRegMaster", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf view.DepartmentMaster
*/
	onInit: function() {
		if (sap.ui.Device.system.phone){
			this.getView().byId("idMasterPage").removeStyleClass("sapMPage").addStyleClass("m-myMasterPage");
		}else{
			this.getView().byId("idMasterPage").removeStyleClass("sapMPage").addStyleClass("myMasterPage");
		}
			
		this.oInitialLoadFinishedDeferred = jQuery.Deferred();
		var oList = this.getView().byId("list");
		oList.attachEvent("updateFinished", function() {
				this.oInitialLoadFinishedDeferred.resolve();
				oEventBus.publish("DepartmentMaster", "InitialLoadFinished");
			}, this);
			
			//On phone devices, there is nothing to select from the list. There is no need to attach events.
			if (sap.ui.Device.system.phone) {
				return;
			}

		//	this.getRouter().attachRoutePatternMatched(this.onRouteMatched, this);

			//oEventBus.subscribe("DepartmentDetail", "Changed", this.onDetailChanged, this);
			//oEventBus.subscribe("DepartmentDetail", "NotFound", this.onNotFound, this);
//			
},
navBackToMenuRederer:function(oEvent){
	debugger;
	this.getRouter().myNavToWithoutHash({ 
		currentView : this.getView(),
		targetViewName : "test.my.fiori.view.MenuRenderer",
		targetViewType : "XML",
		context:null
	});
	
},
onDetailTabChanged : function (sChanel, sEvent, oData) {
	this.sTab = oData.sTabKey;
},

onDetailChanged : function (sChanel, sEvent, oData) {
	var sEntityPath = oData.sEntityPath;
	//Wait for the list to be loaded once
	this.waitForInitialListLoading(function () {
		var oList = this.getView().byId("list");

		var oSelectedItem = oList.getSelectedItem();
		// The correct item is already selected
		if(oSelectedItem && oSelectedItem.getBindingContext().getPath() === sEntityPath) {
			return;
		}

		var aItems = oList.getItems();

		for (var i = 0; i < aItems.length; i++) {
			if (aItems[i].getBindingContext().getPath() === sEntityPath) {
				oList.setSelectedItem(aItems[i], true);
				break;
			}
		}
	});
},
getEventBus : function () {
	return sap.ui.getCore().getEventBus();
},

getRouter : function () {
	return sap.ui.core.UIComponent.getRouterFor(this);
},
onSelect : function(oEvent) {
	debugger;
	// Get the list item either from the listItem parameter or from the event's
	// source itself (will depend on the device-dependent mode)
	this.showDetail(oEvent.getParameter("listItem") || oEvent.getSource());
},
handleAddNewRecord:function(oEvent){
	this.getRouter().myNavToWithoutHash({ 
		currentView : this.getView().getParent().oParent.getParent().oParent,
		targetViewName : "test.my.fiori.view.NewRegCreate",
		targetViewType : "XML",
		context:null,
		appId:"idSplitApp",
	});
//	var oPages =  this.getView().getParent().getParent().getDetailPages();
//	var oView = this.getTargetView(oPages, "create");
	//oView.getController()._toggleButtonsAndView(true);
},
getTargetView:function(pages,type){
	for(var page in pages){
		if(pages[page].getController().getViewFor()==type){
			return pages[page];
		}
	}
	return null;
},
onSearch : function() {
	this.oInitialLoadFinishedDeferred = jQuery.Deferred();
	// Add search filter
	var filters = [];
	var searchString = this.getView().byId("searchField").getValue();
	if (searchString && searchString.length > 0) {
		filters = [ new sap.ui.model.Filter("PatientName", sap.ui.model.FilterOperator.Contains, searchString) ];
	}
	// Update list binding
	this.getView().byId("list").getBinding("items").filter(filters);
	
	//On phone devices, there is nothing to select from the list
	if (sap.ui.Device.system.phone) {
		return;
	}

	//Wait for the list to be reloaded
	this.waitForInitialListLoading(function () {
		//On the empty hash select the first item
		this.selectFirstItem();
	});
},
waitForInitialListLoading : function (fnToExecute) {
	jQuery.when(this.oInitialLoadFinishedDeferred).then(jQuery.proxy(fnToExecute, this));
},
selectFirstItem : function() {
	var oList = this.getView().byId("list");
	var aItems = oList.getItems();
	if (aItems.length) {
		oList.setSelectedItem(aItems[0], true);
		//Load the detail view in desktop
        this.showDetail(aItems[0]);
		oList.fireSelect({"listItem" : aItems[0]});
	}
	else {
		this.getRouter().myNavToWithoutHash({ 
			currentView : this.getView(),
			targetViewName : "test.my.fiori.view.NotFound",
			targetViewType : "XML",
			appId:"idSplitApp",
			isMaster:false
    	});
	}
},
showDetail : function(oItem) {
	// If we're on a phone device, include nav in history
	debugger;
	this.getRouter().myNavToWithoutHash({ 
		currentView : this.getView().getParent().oParent.getParent().oParent,
		targetViewName : "test.my.fiori.view.NewRegDetail",
		targetViewType : "XML",
		context:oItem.getBindingContext(),
		appId:"idSplitApp",
	});
	var oPages =  this.getView().getParent().getParent().getDetailPages();
	var oView = this.getTargetView(oPages, "detail");
	oView.getController()._showFormFragment("Read");
},
/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf view.SexView
*/
//onBeforeRendering: function() {
//
//},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf view.SexView
*/
onAfterRendering: function() {
	if (!sap.ui.Device.system.phone) {
		this.waitForInitialListLoading(function () {
			//On the empty hash select the first item
			this.selectFirstItem();
		});
	}
},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf view.SexView
*/
onExit: function() {
	this.getView().byId("list").removeSelections();
},
handleEditPress:function(oEvent){
	alert("hi");
}
});
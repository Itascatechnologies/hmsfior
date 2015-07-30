sap.ui.controller("test.my.fiori.view.DepartmentCreate", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf view.DepartmentDetail
*/
	onInit: function() {
		if (sap.ui.Device.system.phone) 
			this.getView().byId("idCreatePage").addStyleClass("m-myDetailPage");
		else
			this.getView().byId("idCreatePage").addStyleClass("myDetailPage");
	},
	onRouteMatched : function(oEvent) {
		
	},
/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf view.DepartmentDetail
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf view.DepartmentDetail
*/
	onAfterRendering: function() {
     
	},
	getViewFor:function(){
		return "create";
	},
/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf view.DepartmentDetail
*/
	onExit: function() {
       //this.removeDialogButtons();
	},
	validationName:function(name){
		if(name==""){
			this.oAlertDialog.close();
			sap.m.MessageToast.show("Please Enter Department Name");
	            return false;
	    }else if(!isNaN(name)){
	    	this.oAlertDialog.close();
	    	sap.m.MessageToast.show("Please Enter Department Name  Only In Characters");
	            return false;
	    }
		return true;
	},
	onDialogConfirmed:function(oEvent){
		var oEntry = {};
		var oModel = this.getView().getModel();
		if(!this.validationName(this.getView().byId("idName").getValue())){
			this.getView().byId("idName").setValueState(sap.ui.core.ValueState.Error);
			return;
		}else{
			this.getView().byId("idName").setValueState(sap.ui.core.ValueState.None);
		}
		oEntry.Name = this.getView().byId("idName").getValue();
		oEntry.Description = this.getView().byId("idDescription").getValue();
		var oView = this;
		var bundle =this.getView().getModel("i18n").getResourceBundle();
		 oModel.create('/Departments', oEntry, null, function(){
		     oView.getView().byId("idName").setValue("");
		     oView.getView().byId("idDescription").setValue("");
			 oView.oAlertDialog.close(); 
	 		  sap.m.MessageToast.show(bundle.getText("messageSuccessfullSubmited"));
	 		  oView.navToEmptyViewForWindowCase();
	 	},function(){

	 		sap.m.MessageToast.show(bundle.getText("messageFailureSubmited"));;});
	},
	onDialogDeleted:function(oEvent){
		
	},
	onDialogClosed:function(oEvent){
		this.oAlertDialog.close();
	},
	handleSaveButtonPress:function(oEvent){
		if (!this.oAlertDialog) {
			this.oAlertDialog = sap.ui.xmlfragment("test.my.fiori.fragments.dialog.Confirmation", this);
			this.getView().addDependent(this.oAlertDialog);
		}
		this.oAlertDialog.getButtons()[0].setVisible(false);
		this.oAlertDialog.getButtons()[1].setVisible(true);
		this.oAlertDialog.getButtons()[2].setVisible(true);
		this.oAlertDialog.open();
	},
	navToEmptyViewForWindowCase:function(){
		
			this.getRouter().myNavToWithoutHash({ 
	    		currentView : this.getView().getParent().oParent.getParent().oParent,
	    		targetViewName : "test.my.fiori.view."+this.toggleNavPage().id,
	    		targetViewType : "XML",
	    		context:this.getView().getBindingContext(),
	    		appId:"idSplitApp",
	    		isMaster:this.toggleNavPage().isMaster
	    	});
	
		
	},
	toggleNavPage:function(){
		if (sap.ui.Device.system.phone) 
			return { id:"DepartmentMaster",isMaster:true};
		else
			return {id:"Empty",isMaster:false};
	},
	getEventBus : function () {
		return sap.ui.getCore().getEventBus();
	},

	getRouter : function () {
		return sap.ui.core.UIComponent.getRouterFor(this);
	},
	_toggleButtonsAndView:function(bFlag){},
});
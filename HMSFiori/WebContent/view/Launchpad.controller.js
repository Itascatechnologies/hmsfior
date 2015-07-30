sap.ui.controller("test.my.fiori.view.Launchpad", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf view.Launchpad
*/
	onInit: function() {
		//var oEventBus = this.getEventBus();
		debugger;
		this.getRouter().attachRoutePatternMatched(this.onRouteMatched, this);
		//oEventBus.subscribe("AppConfigView", "navTo", this.onNavTo, this);

	},
	
	onRouteMatched : function(oEvent) {
		var sName = oEvent.getParameter("name");
       
		if (sName !== "main") {
			return;
		}
	},
	onNavTo : function (sChanel, sEvent, oData) {
		this.sTab = oData.sTabKey;
	},
	handleTilePress:function(oEvent){
		this.getRouter().myNavToWithoutHash({ 
			currentView : this.getView(),
			targetViewName : "test.my.fiori.view.MenuRenderer",
			targetViewType : "XML",
			context:oEvent.getSource().getBindingContext()
    	});
	},
	getEventBus : function () {
		return sap.ui.getCore().getEventBus();
	},

	getRouter : function () {
		return sap.ui.core.UIComponent.getRouterFor(this);
	},
	
	bindView : function (sEntityPath) {
		var oView = this.getView();
		oView.bindElement(sEntityPath);

	},
/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf view.Launchpad
*/
	onBeforeRendering: function() {
		debugger;
		//this.bindView(this.getView().getModel().getContext("/Menus").sPath);
		
	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf view.Launchpad
*/
	onAfterRendering: function() {

	},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf view.Launchpad
*/
//	onExit: function() {
//
//	}
	
	

});
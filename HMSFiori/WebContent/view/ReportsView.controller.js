sap.ui.controller("test.my.fiori.view.ReportsView", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf view.ReportView
*/
	onInit: function(oEvent) {
		this.getRouter().attachRouteMatched(this.onRouteMatched, this);
	},
	onNavTo : function (sChanel, sEvent, oData) {
		this.sTab = oData.sTabKey;
	},
	
	onRouteMatched : function(oEvent) {
		var oParameters = oEvent.getParameters();
		if (oParameters.name !== "ReportsView") { 
			return;
		}
		var sEntityPath = "/" + oParameters.arguments.entity;
		this.bindView(sEntityPath);
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
* @memberOf view.ReportView
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf view.ReportView
*/
//	onAfterRendering: function() {
//
//	},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf view.ReportView
*/
//	onExit: function() {
//
//	}

});
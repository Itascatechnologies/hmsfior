sap.ui.controller("test.my.fiori.view.AppConfigView", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf view.AppConfig
*/
	onInit: function(oEvent) {
		debugger;
//		var oEventBus = this.getEventBus();
//		var sEntityPath = "/Menus(4)";
//		this.bindView(sEntityPath);
		this.getRouter().attachRouteMatched(this.onRouteMatched, this);
		
	},
	onNavTo : function (sChanel, sEvent, oData) {
		this.sTab = oData.sTabKey;
	},
	
	onRouteMatched : function(oEvent) {
		debugger;
		var oParameters = oEvent.getParameters();
		alert(oParameters.name);
		if (oParameters.name !== "appconfig") { 
			return;
		}
		var sEntityPath = "/" + oParameters.arguments.entity;
		this.bindView(sEntityPath);
	},

	navBackToAppConfigView:function(oEvent){
		alert('hi');
	},
	onSelect:function(oEvent){
		var bReplace = jQuery.device.is.phone ? false : true;
		this.getRouter().navTo("SexView", {
			from: "main",
		}, bReplace);

	},
	
	getEventBus : function () {
		return sap.ui.getCore().getEventBus();
	},

	getRouter : function () {
		debugger;
		return sap.ui.core.UIComponent.getRouterFor(this);
	},
/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf view.AppConfig
*/ 
	onBeforeRendering: function(oEvent) {
//		 if (! this._oDialog) {
//		      this._oDialog = sap.ui.xmlfragment("test.my.fiori.view.Menus", this);
//		      //this._oDialog.setModel(this.getView().getModel());
//		  }
//		   jQuery.sap.syncStyleClass("sapUiSizeCompact", this.getView(), this._oDialog);
//		    this._oDialog.open();
	},
	bindView : function (sEntityPath) {
		var oView = this.getView();
		oView.bindElement(sEntityPath);

	},
	
/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf view.AppConfig
*/
//	onAfterRendering: function() {
//
//	},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf view.AppConfig
*/
//	onExit: function() {
//
//	}

});
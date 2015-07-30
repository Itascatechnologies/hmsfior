sap.ui.controller("test.my.fiori.view.RegFeeView", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf view.DepartmentView
*/
//	onInit: function() {
//
//	},
	navBackToMenuRederer:function(oEvent){
		debugger;
		this.getRouter().myNavToWithoutHash({ 
			currentView : this.getView(),
			targetViewName : "test.my.fiori.view.MenuRenderer",
			targetViewType : "XML",
			context:null
    	});
	},
/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf view.DepartmentView
*/
	onBeforeRendering: function() {
		 debugger;
		   this.getRouter().myNavToWithoutHash({ 
				currentView : this.getView(),
				targetViewName : "test.my.fiori.view.RegFeeMaster",
				targetViewType : "XML",
				context:null,
				appId:"idSplitApp",
				isMaster:true,
	    	});
	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf view.DepartmentView
*/
//	onAfterRendering: function() {
//
//	},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf view.DepartmentView
*/
//	onExit: function() {
//
//	},
	getEventBus : function () {
		return sap.ui.getCore().getEventBus();
	},

	getRouter : function () {
		
		return sap.ui.core.UIComponent.getRouterFor(this);
	},

});
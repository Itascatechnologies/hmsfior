sap.ui.controller("test.my.fiori.view.NewRegView", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf view.NewRegView
*/
//	 _data : {
//	      "date" : new Date()
//	    },

	onInit: function() {
		
	},
//	handleSelectChange:function(oEvent){
//		var oContext = oEvent.getSource().getBindingContext().getObject();
//		
//		var rr=90
//		
//	},
//	handleCancelButtonPress:function(){
//		this.navBackToMenuRederer();
//	},
	navBackToMenuRederer:function(){
			this.getRouter().myNavToWithoutHash({ 
			currentView : this.getView(),
			targetViewName : "test.my.fiori.view.MenuRenderer",
			targetViewType : "XML",
			context:null
    	});
		
	},
//	
	getEventBus : function () {
		return sap.ui.getCore().getEventBus();
	},

	getRouter : function () {
		return sap.ui.core.UIComponent.getRouterFor(this);
	},


/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf view.NewRegView
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf view.NewRegView
*/
	onAfterRendering: function() {
		
		this.getRouter().myNavToWithoutHash({ 
			currentView : this.getView(),
			targetViewName : "test.my.fiori.view.NewRegMaster",
			targetViewType : "XML",
			context:null,
			appId:"idSplitApp",
			isMaster:true,
    	});
	
	},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf view.NewRegView
*/
	onExit: function() {
		
	}

});
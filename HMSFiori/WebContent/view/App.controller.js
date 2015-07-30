sap.ui.core.mvc.Controller.extend("test.my.fiori.view.App", {
	init:function(){
		//oEventBus.subscribe("Launchpad", "navTo", this.onNavTo, this);
		//oEventBus.subscribe("Launchpad", "navTo", this.onNavTo, this);
	},
	
	onNavTo : function (sChanel, sEvent, oData) {
		this.sTab = oData.sTabKey;
	},
	handlePressConfiguration:function(){
		debugger;
//		this.getRouter().myNavToWithoutHash({ 
//			currentView : this.getView(),
//			targetViewName : "test.my.fiori.view.Launchpad",
//			targetViewType : "XML",
//			context:this.getView().getModel().getContext("/Menus")
//    	});
		window.location.reload(true);
	},
	getEventBus : function () {
		return sap.ui.getCore().getEventBus();
	},

	getRouter : function () {
		return sap.ui.core.UIComponent.getRouterFor(this);
	},
	onAfterRendering: function() {
		//debugger;
		this.getRouter().myNavToWithoutHash({ 
			currentView : this.getView(),
			targetViewName : "test.my.fiori.view.Launchpad",
			targetViewType : "XML",
			context:this.getView().getModel().getContext("/Menus")
    	});	
	},
	
});
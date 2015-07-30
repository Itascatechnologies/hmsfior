sap.ui.controller("test.my.fiori.view.MenuRenderer", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf view.MenuRenderer
*/
//	onInit: function() {
//
//	},
	onTap:function(oEvent){	 
	  var oContext = this.getView().getBindingContext();	
	  this.getView().byId("list").removeSelections();
	  this.getRouter().myNavToWithoutHash({ 
				currentView : this.getView(),
				targetViewName : "test.my.fiori.view."+oEvent.getSource().getBindingContext().getObject().TargetPage,
				targetViewType : "XML",
				context:oContext
	    	});
	 
	},
	onPress:function(oEvent){
		alert('press');
	},
	onSelect:function(oEvent){
		debugger;
		 var oContext = this.getView().getBindingContext();	 
		 this.getView().byId("list").removeSelections();
		 this.getRouter().myNavToWithoutHash({ 
				currentView : this.getView(),
				targetViewName : "test.my.fiori.view."+oEvent.getParameter("listItem").getBindingContext().getObject().TargetPage,
				targetViewType : "XML",
				context:oContext
	    	});
	},
	getEventBus : function () {
		return sap.ui.getCore().getEventBus();
	},

	getRouter : function () {
		//debugger;
		return sap.ui.core.UIComponent.getRouterFor(this);
	},
/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf view.MenuRenderer
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf view.MenuRenderer
*/
//	onAfterRendering: function() {
//
//	},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf view.MenuRenderer
*/
	onExit: function() {
		this.getView().byId("list").removeSelections();
	}

});
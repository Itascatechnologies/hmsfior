sap.ui.controller("test.my.fiori.view.NewTestRegDetail", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf view.RenewRegistration
*/
	onInit: function() {
		
	},
	handleRenewButtonPress:function(){
		if (!this.oAlertDialog) {
			this.oAlertDialog = sap.ui.xmlfragment("test.my.fiori.fragments.dialog.Confirmation", this);
			this.getView().addDependent(this.oAlertDialog);
		}
		this.oAlertDialog.getButtons()[0].setVisible(false);
		this.oAlertDialog.getButtons()[1].setVisible(true);
		this.oAlertDialog.getButtons()[2].setVisible(true);
		this.oAlertDialog.open();
	},
	navBackToMenuRederer:function(){
		this.getRouter().myNavToWithoutHash({ 
			currentView : this.getView(),
			targetViewName : "test.my.fiori.view.MenuRenderer",
			targetViewType : "XML",
			context:null
    	});
	},
	getEventBus : function () {
		return sap.ui.getCore().getEventBus();
	},

	getRouter : function () {
		
		return sap.ui.core.UIComponent.getRouterFor(this);
	},
/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf view.RenewRegistration
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf view.RenewRegistration
*/
	onAfterRendering: function() {
		
	
	},
onDialogConfirmed:function(oEvent){
		
		var oModel = this.getView().getModel();
		var oEntry=this.getView().getBindingContext().getObject();

		var oController = this;
		var bundle = this.getView().getModel("i18n").getResourceBundle();
		oModel.create('/Registrations', oEntry, null, function(){
			oController.oAlertDialog.close();
			// clearing form input fields
	 		 sap.m.MessageToast.show(bundle.getText("messageSuccessfullSubmited"));
	 		oController.navBackToMenuRederer();
	 	},function(){
	 		sap.m.MessageToast.show(bundle.getText("messageFailureSubmited"));;});
	},
	onDialogClosed:function(oEvent){
		this.oAlertDialog.close();
	},
	
/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf view.RenewRegistration
*/
//	onExit: function() {
//
//	},
	

});
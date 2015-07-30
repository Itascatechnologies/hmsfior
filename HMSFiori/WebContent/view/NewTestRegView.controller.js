sap.ui.controller("test.my.fiori.view.NewTestRegView", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf view.RenewRegistration
*/
	onInit: function() {
		
	},
	onSearch:function(oEvent){
		var registrationId = this.getView().byId("searchField").getValue();
		var oController = this;
		var oModel = this.getView().getModel();
		var bundle = this.getView().getModel("i18n").getResourceBundle();
		oController._showFormFragment("Edit");
		oModel.read('/Registrations('+registrationId+')', null, null, true, function(oData, oResponse){
				var oView = oController.getView();
				oView.bindElement('/Registrations('+registrationId+')'); 
				var oContext = oModel.getContext('/Registrations('+registrationId+')'); 
			    oController._showFormFragment("Edit");
	 	},function(){
	 		sap.m.MessageToast.show("Registraton no : "+registrationId+" does not exits, please enter correct registration no");
	 		});
	},
	getEventBus : function () {
		return sap.ui.getCore().getEventBus();
	},

	getRouter : function () {
		
		return sap.ui.core.UIComponent.getRouterFor(this);
	},
	_showFormFragment : function(fType) {
		var oController = this;
		var oPage = this.getView().byId("addTestGrid");
		var sFragmentName = "AddTest_Edit";
		oPage.removeAllContent();
		oPage.destroyContent();
		oPage.insertContent(this._createFragment(sFragmentName));
//		var b1 = new sap.m.Button({
//        	text:"Renew",
//        	type:"Emphasized",
//        	press:this.handleRenewButtonPress
//        });
//		var b2= new sap.m.Button({
//        	text:"Cancel",
//        	press:this.navBackToMenuRederer
//        });
//		var oBar  = new sap.m.Bar({
//			contentRight:[b1,b2]
//		}).addStyleClass("myBar");
//		oPage.addContent(oBar);
	},
	handleRenewButtonPress:function(){
		
		var oController = this.getParent().getParent().getParent().getController();
		if (!oController.oAlertDialog) {
			oController.oAlertDialog = sap.ui.xmlfragment("test.my.fiori.fragments.dialog.Confirmation", oController);
			oController.getView().addDependent(oController.oAlertDialog);
		}
		oController.oAlertDialog.getButtons()[0].setVisible(false);
		oController.oAlertDialog.getButtons()[1].setVisible(true);
		oController.oAlertDialog.getButtons()[2].setVisible(true);
		oController.oAlertDialog.open();
	},
	navBackToMenuRederer:function(){
		var oController = this;
		if(this.getRouter==null){
			oController = this.getParent().getParent().getParent().getController();
		}
		
//		var oImage = new sap.m.Image({
//	          src:"images/building.jpg",
//	          width:"98%",
//	          height:"80%"
//			}).addStyleClass("building");
//			var oGrid = oController.getView().byId("addTestGrid");
//			oController.getView().byId("searchField").setValue("");
//			oGrid.removeAllContent();
//			oGrid.insertContent(oImage);
		
		oController.getRouter().myNavToWithoutHash({ 
			currentView : oController.getView(),
			targetViewName : "test.my.fiori.view.MenuRenderer",
			targetViewType : "XML",
			context:null
    	});
		
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

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf view.RenewRegistration
*/
//	onExit: function() {
//
//	}
	_fragmentChache:[],
	_getFragment:function(name){
		return this._fragmentChache[name];
	},
	_createFragment:function(fName){
		if(this._fragmentChache[fName]==null){
			this._fragmentChache[fName] =sap.ui.xmlfragment(this.getView().getId(), "test.my.fiori.fragments." + fName);
		}
		return this._fragmentChache[fName];
	}

});
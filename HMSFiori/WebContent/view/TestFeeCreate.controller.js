sap.ui.controller("test.my.fiori.view.TestFeeCreate", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf view.TestFeeDetail
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
* @memberOf view.TestFeeDetail
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf view.TestFeeDetail
*/
	onAfterRendering: function() {
     
	},
	getViewFor:function(){
		return "create";
	},
/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf view.TestFeeDetail
*/
	onExit: function() {
       //this.removeDialogButtons();
	},
	validateFeeAmount:function(value){
		var myInteger = (/^-?\d*(\.\d+)?$/);
	 if(value==""){
		 this.oAlertDialog.close();
			sap.m.MessageToast.show("Please Enter Test Fee Amount");
			return false
		}
		else if(!value.match(myInteger)){
			this.oAlertDialog.close();
			sap.m.MessageToast.show("Invalid Test Fee Amount. Please Enter Valid Amount");
			return false;
		}
		return true;
	},
	onDialogConfirmed:function(oEvent){
		var oEntry = {};
		var oModel = this.getView().getModel();
		oEntry.IdTestMaster = parseInt(this.getView().byId("idSelectTestType").getSelectedItem().getKey());
		
		if(!this.validateFeeAmount(this.getView().byId("idTestRate").getValue())){
			this.getView().byId("idTestRate").setValueState(sap.ui.core.ValueState.Error);
			return;
		}else{
			this.getView().byId("idTestRate").setValueState(sap.ui.core.ValueState.None);
		}
		oEntry.Rate = this.getView().byId("idTestRate").getValue();
	
		
		var oView = this;
		var bundle =this.getView().getModel("i18n").getResourceBundle();
		 oModel.create('/TestRatesMasters', oEntry, null, function(){
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
			return { id:"TestFeeMaster",isMaster:true};
		else
			return {id:"Empty",isMaster:false};
	},
	getEventBus : function () {
		return sap.ui.getCore().getEventBus();
	},

	getRouter : function () {
		return sap.ui.core.UIComponent.getRouterFor(this);
	},
	_toggleButtonsAndView : function(bEdit) {
		
	},
});
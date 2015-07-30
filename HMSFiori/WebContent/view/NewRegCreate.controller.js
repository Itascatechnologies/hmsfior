sap.ui.controller("test.my.fiori.view.NewRegCreate", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf view.DepartmentDetail
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
* @memberOf view.DepartmentDetail
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf view.DepartmentDetail
*/
	onAfterRendering: function() {
		var bundle = this.getView().getModel("i18n").getResourceBundle();
		this.getView().byId("idRegFeeAmountText").setText(bundle.getText("messageTextRegFee"));
	},
	getViewFor:function(){
		return "create";
	},
/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf view.DepartmentDetail
*/
	onExit: function() {
		var bundle = this.getView().getModel("i18n").getResourceBundle();
		this.getView().byId("idRegFeeAmountText").setText(bundle.getText("messageTextRegFee"));
	},
	
	onDialogDeleted:function(oEvent){
		
	},
	handleSelectChangePatientCategory:function(oEvent){
		this.getView().byId("idRegFeeAmountText").setText(oEvent.getSource().getSelectedItem().getBindingContext().getObject().Fee);
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
			return { id:"NewRegMaster",isMaster:true};
		else
			return {id:"Empty",isMaster:false};
	},
	getEventBus : function () {
		return sap.ui.getCore().getEventBus();
	},

	getRouter : function () {
		return sap.ui.core.UIComponent.getRouterFor(this);
	},
	validationName:function(name,m1){
		if(name==""){
			this.oAlertDialog.close();
			sap.m.MessageToast.show("Please Enter "+m1);
	            return false;
	    }else if(!isNaN(name)){
	    	this.oAlertDialog.close();
	    	sap.m.MessageToast.show("Please Enter"+m1+" Only Characters");
	            return false;
	    }
		return true;
	},
	validateContactNo:function(contactNo){
		var myInteger = (/^-?\d*(\.\d+)?$/);
		if(contactNo==""){
			return true;
		}else if(!contactNo.match(myInteger)){
			this.oAlertDialog.close();
			sap.m.MessageToast.show("Patient Contact No Should Be Number Only");
			return false;
		}else if(contactNo.length!=10){
			this.oAlertDialog.close();
			sap.m.MessageToast.show("Invalid Patient Contact No");
			return false
		}
		return true;
	},
	validateAge:function(age){
		var myInteger = (/^-?\d*(\.\d+)?$/);
	 if(age==""){
		 this.oAlertDialog.close();
			sap.m.MessageToast.show("Patient Enter Patient Age");
			return false
		}
		else if(!age.match(myInteger)){
			this.oAlertDialog.close();
			sap.m.MessageToast.show("Patient Age Should Be Number Only");
			return false;
		}else if(age<1){
			this.oAlertDialog.close();
			sap.m.MessageToast.show("Age "+age+" Not A Valid Age ");
			return false
		}else if(age>120){
			this.oAlertDialog.close();
			sap.m.MessageToast.show("Age "+age+" Not A Valid Age ");
			return false
		}
		return true;
	},
	onDialogConfirmed:function(oEvent){
		
		var oModel = this.getView().getModel();
		var oEntry={};//IdPatientCategory
		oEntry.IdPatientCategory = parseInt(this.getView().byId("idSelectCategory").getSelectedItem().getKey()); //this.getView().byId("idChoiceCategory").getSelectedButton().getBindingContext().getObject().Id;
		if(!this.validationName(this.getView().byId("idPatientName").getValue(), "Patient Name")){
			this.getView().byId("idPatientName").setValueState(sap.ui.core.ValueState.Error);
			return;}else{
				this.getView().byId("idPatientName").setValueState(sap.ui.core.ValueState.None);
			}
		oEntry.PatientName = this.getView().byId("idPatientName").getValue();
		if(!this.validationName(this.getView().byId("idPatientFathersName").getValue(), "Patient Father's Name")){
				this.getView().byId("idPatientFathersName").setValueState(sap.ui.core.ValueState.Error);
			return;}else{
				this.getView().byId("idPatientFathersName").setValueState(sap.ui.core.ValueState.None);
			}
		oEntry.PatientFathersName = this.getView().byId("idPatientFathersName").getValue();	
		if(!this.validateAge(this.getView().byId("idPatientAge").getValue())){
			this.getView().byId("idPatientAge").setValueState(sap.ui.core.ValueState.Error);
			return;
		}else{
			this.getView().byId("idPatientAge").setValueState(sap.ui.core.ValueState.None);
		}
		oEntry.PatientAge = this.getView().byId("idPatientAge").getValue();
		oEntry.PatientAgeIn = this.getView().byId("idSelectAgeIn").getSelectedItem().getKey();
		if(!this.validateContactNo(this.getView().byId("idPatientContactNo").getValue())){
			this.getView().byId("idPatientContactNo").setValueState(sap.ui.core.ValueState.Error);
			return;
		}else{
			this.getView().byId("idPatientContactNo").setValueState(sap.ui.core.ValueState.None);
		}
		oEntry.PatientContactNo = this.getView().byId("idPatientContactNo").getValue();
		oEntry.PatientAddress = this.getView().byId("idPatientAddress").getValue();//
		oEntry.IdDepartment = parseInt(this.getView().byId("idSelectDepartment").getSelectedItem().getKey());
		oEntry.IdSex = parseInt(this.getView().byId("idSelectGender").getSelectedItem().getKey());
		
		var oController = this;
		var bundle = this.getView().getModel("i18n").getResourceBundle();
		oModel.create('/Registrations', oEntry, null, function(){
			oController.oAlertDialog.close();
			// clearing form input fields
			 oController.getView().byId("idPatientName").setValue("");
			 oController.getView().byId("idPatientFathersName").setValue("");	
			 oController.getView().byId("idPatientAge").setValue("");
			 oController.getView().byId("idPatientContactNo").setValue("");
			 oController.getView().byId("idPatientAddress").setValue("");
			 oController.getView().byId("idRegFeeAmountText").setText(bundle.getText("messageTextRegFee"));
	 		 sap.m.MessageToast.show(bundle.getText("messageSuccessfullSubmited"));
	 		oController.navToEmptyViewForWindowCase();
	 	},function(){
	 		sap.m.MessageToast.show(bundle.getText("messageFailureSubmited"));;});
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
	_toggleButtonsAndView:function(bFlag){},
});
sap.ui.controller("test.my.fiori.view.PatCatDetail", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf view.SexDetail
*/
	onInit: function(oEvent) {
		debugger;
		if (sap.ui.Device.system.phone) 
			this.getView().byId("idDetailPage").addStyleClass("m-myDetailPage");
		else
			this.getView().byId("idDetailPage").addStyleClass("myDetailPage");
		this.getRouter().attachRouteMatched(this.onRouteMatched, this);
		this.oInitialLoadFinishedDeferred = jQuery.Deferred();
		
				if(sap.ui.Device.system.phone) {
					//Do not wait for the master when in mobile phone resolution
					this.oInitialLoadFinishedDeferred.resolve();
				} else {
					//this.getView().setBusy(true);
					var oEventBus = this.getEventBus(); 
					oEventBus.subscribe("Component", "MetadataFailed", this.onMetadataFailed, this);
					oEventBus.subscribe("PatCatMaster", "InitialLoadFinished", this.onMasterLoaded, this);
				}
		
				
	},
	onRouteMatched : function(oEvent) {
		var oParameters = oEvent.getParameters();

		jQuery.when(this.oInitialLoadFinishedDeferred).then(jQuery.proxy(function () {
		var oView = this.getView();

		// When navigating in the Detail page, update the binding context 
		alert(oParameters.name);
		if (oParameters.name !== "PatCatDetail") { 
			return;
		}

		var sEntityPath = "/" + oParameters.arguments.entity;
		this.bindView(sEntityPath);

		var oIconTabBar = oView.byId("idIconTabBar");
		oIconTabBar.getItems().forEach(function(oItem) {
		    if(oItem.getKey() !== "selfInfo"){
				oItem.bindElement(oItem.getKey());
		    }
		});

		// Specify the tab being focused
		var sTabKey = oParameters.arguments.tab;
		this.getEventBus().publish("PatCatDetail", "TabChanged", { sTabKey : sTabKey });

		if (oIconTabBar.getSelectedKey() !== sTabKey) {
			oIconTabBar.setSelectedKey(sTabKey);
			}
		}, this));
	},
	
	handleEditPress:function(oEvent){
		var oPage = this.getView().byId("idDetailPage");
	    oPage.removeAllContent();
	    this._toggleButtonsAndView(true);
	    
	},
	 _toggleButtonsAndView : function (bEdit) {
		    var oView = this.getView();

		    // Show the appropriate action buttons
		    oView.byId("edit").setVisible(!bEdit);
		    oView.byId("delete").setVisible(!bEdit);
		    oView.byId("save").setVisible(bEdit);
		    oView.byId("cancel").setVisible(bEdit);

		    // Set the right form type
		    this._showFormFragment(bEdit ? "Edit" : "Read");
	},
	_showFormFragment : function (fType) {
			    var oPage = this.getView().byId("idDetailPage");
                var sFragmentName ="PatientCategory_Read";
                if(fType=="Edit"){
                	sFragmentName ="PatientCategory_Edit";
                }
			    oPage.removeAllContent();
			    oPage.destroyContent();
			    oPage.insertContent( new FragmentFactory()._getRequestedFragment(sFragmentName, this.getView(), fType));
			    
			    
			    
	},
	handleSavePress : function() {
		debugger;
		if (!this.oAlertDialog) {
			this.oAlertDialog = sap.ui.xmlfragment("test.my.fiori.fragments.dialog.Confirmation", this);
			this.getView().addDependent(this.oAlertDialog);
		}
		//if(this.oAlertDialog.getButtons()>0){
			this.oAlertDialog.getButtons()[0].setVisible(false);
			this.oAlertDialog.getButtons()[1].setVisible(true);
			this.oAlertDialog.getButtons()[2].setVisible(true);
			this.oAlertDialog.open();
		//}
		

	},
	onDialogConfirmed:function(oEvent){
		var oEntry = this.getView().getBindingContext().getObject();
		var path = this.getView().getBindingContext().sPath.substr(1) + '/';
		var oView = this;
		var oModel = this.getView().getModel();
		var bundle =this.getView().getModel("i18n").getResourceBundle();
		 oModel.update(path, oEntry, null, function(){
			 oView.oAlertDialog.close();
		     sap.m.MessageToast.show(bundle.getText("messageSuccessfullUpdated"));
		     if (sap.ui.Device.system.phone)
	 			    oView.navToEmptyViewForWindowCase();
	 			else
	 				oView._toggleButtonsAndView(false);
	 	},function(){
	 		sap.m.MessageToast.show(bundle.getText("messageFailureUpdated"));;});
	},
	onDialogClosed:function(oEvent){
		this.oAlertDialog.close();
	},
	handleCancelPress : function() {

		this._toggleButtonsAndView(false);

	},
	onDialogDeleted:function(oEvent){
		var path = "/" + this.getView().getBindingContext().sPath.substr(1);
		var oModel = this.getView().getModel();
		var oView =this;
		var bundle =this.getView().getModel("i18n").getResourceBundle();
		 oModel.remove(path, null, function(){
			 oView.oAlertDialog.close();
			 sap.m.MessageToast.show(bundle.getText("messageSuccessfullDeleted"));
	 		 oView.navToEmptyViewForWindowCase();
	 	},function(){
	 		sap.m.MessageToast.show(bundle.getText("messageFailureDeleted"));;});
	},
	handleDeletePress : function() {
		if (!this.oAlertDialog) {
			this.oAlertDialog = sap.ui.xmlfragment("test.my.fiori.fragments.dialog.Confirmation", this);
			this.getView().addDependent(this.oAlertDialog);
		}
			this.oAlertDialog.getButtons()[0].setVisible(true);
			this.oAlertDialog.getButtons()[1].setVisible(false);
			this.oAlertDialog.getButtons()[2].setVisible(true);
			
		
		this.oAlertDialog.open();

	},
	toggleNavPage:function(){
		if (sap.ui.Device.system.phone) 
			return { id:"PatCatMaster",isMaster:true};
		else
			return {id:"Empty",isMaster:false};
	},
	navToEmptyViewForWindowCase:function(){
		this.getRouter().myNavToWithoutHash({ 
    		currentView : this.getView().getParent().oParent.getParent().oParent,
    		targetViewName : "test.my.fiori.view."+this.toggleNavPage().id,
    		targetViewType : "XML",
    		context:null,
    		appId:"idSplitApp",
    		isMaster:this.toggleNavPage().isMaster
    	});
	},
	getEventBus : function () {
		return sap.ui.getCore().getEventBus();
	},

	getRouter : function () {
		return sap.ui.core.UIComponent.getRouterFor(this);
	},
	getViewFor:function(){
		return "detail";
	},
});
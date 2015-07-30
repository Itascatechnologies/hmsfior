sap.ui.controller("test.my.fiori.view.SexView", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf view.SexView
*/
	 app:{},
		onInit: function() {
//			this.app =  this.getView().byId("idSplitApp");
//			var oEventBus = this.getEventBus();
//			oEventBus.subscribe("nav", "to", this.navHandler, this);
//			oEventBus.subscribe("nav", "back", this.navHandler, this);
//			//bus.subscribe("nav", "virtual", this.navHandler, this);
//	        var oMaster = this.get("SexMaster");
//	      //  var oDetail = this.get("SexDetail");
//	        this.app.addMasterPage(oMaster,true);
//	       //this.app.addDetailPage(oDetail,false);

		},
		 navHandler: function (channelId, eventId, data) {
			 //debugger;
				if (eventId === "to") {
					this.navTo(data.id, data.data, true);
				} else if (eventId === "back") {
					jQuery.sap.history.back();
				} else if (eventId === "virtual") {
					jQuery.sap.history.addVirtualHistory();
				} else {
					jQuery.sap.log.error("'nav' event cannot be processed. There's no handler registered for event with id: " + eventId);
				}
			},
			
			navTo : function (id, data, writeHistory) {
				
				if (id === undefined) {
					
					// invalid parameter
					jQuery.sap.log.error("navTo failed due to missing id");
				
				} else {
					var master =false;
					if(id=="SexMaster"){
						master = true;
					}
					
					// load view on demand
					
					var page=this.get(id);
					page.getController().getView().setBindingContext(data);
					//page.setModel("item",data.getObject);
					//page.setBindingContext(data);
					// page.removeAllContent();
					// page.getController().onBeforeRendering();
					// oPage.insertContent(this._getFormFragment(sFragmentName));
					 
					if (this.app.getPage(id, master) === null) {
						if (master) {
							this.app.addMasterPage(page);
						} else {
							this.app.addDetailPage(page);
						}
						jQuery.sap.log.info("app controller > loaded page: " + id);
					}
					//page.getController().initService();
					// navigate in the app control
					this.app.to(id, "flip", data);
					
					// write browser history
					if ((writeHistory === undefined || writeHistory) &&
						(sap.ui.Device.system.phone || master)) {
						jQuery.sap.history.addHistory("page", { id: id }, false);
					}
					
					// log
					jQuery.sap.log.info("navTo - to page: " + id + " [" + writeHistory + "]");
				}
			},
			
			navBack : function (id) {
				
				if (!id) {
					
					// invalid parameter
					jQuery.sap.log.error("navBack - parameters id must be given");
				
				} else {
					
					// close open popovers
					if (sap.m.InstanceManager.hasOpenPopover()) {
						sap.m.InstanceManager.closeAllPopovers();
					}
					
					// close open dialogs
					if (sap.m.InstanceManager.hasOpenDialog()) {
						sap.m.InstanceManager.closeAllDialogs();
						jQuery.sap.log.info("navBack - closed dialog(s)");
					}
					
					// ... and navigate back
					//var app = this.getView().app;
					var currentId = (this.app.getCurrentPage()) ? this.app.getCurrentPage().getId() : null;
					if (currentId !== id) {
						this.app.backToPage(id);
						jQuery.sap.log.info("navBack - back to page: " + id);
					}
				}
			},
	/**
	* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
	* (NOT before the first rendering! onInit() is used for that one!).
	* @memberOf view.MasterSpliter
	*/
		onBeforeRendering: function(oEvent) {
		   debugger;
		   this.getRouter().myNavToWithoutHash({ 
				currentView : this.getView(),
				targetViewName : "test.my.fiori.view.SexMaster",
				targetViewType : "XML",
				context:null,
				appId:"idSplitApp",
				isMaster:true,
	    	});
		},
	    oViewCache:[],
	    viewExist:false,
		get : function(viewName) {
			if (this.oViewCache[viewName]==null) {
				var fullViewName =  "test.my.fiori.view." + viewName;
				this.oViewCache[viewName] = sap.ui.view({
					id : viewName,
					viewName : fullViewName,
					type : sap.ui.core.mvc.ViewType.XML
				});
				this.viewExist=false;
			} else {
				this.viewExist=true;
			}
			return this.oViewCache[viewName];
		},

		onAfterRendering: function() {
			
		},
		onExit: function() {
			this.getEventBus().unsubscribe("nav", "to", this.navHandler, this);
			//oEventBus.unsubscribe("Detail", "to", this.navHandler, this);
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
});
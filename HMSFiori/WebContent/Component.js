jQuery.sap.declare("test.my.fiori.Component");
jQuery.sap.require("test.my.fiori.MyRouter");

sap.ui.core.UIComponent.extend("test.my.fiori.Component", {
	metadata : {
		name : "MasterToDetails",
		version : "1.0",
		includes : [],
		dependencies : {
			libs : ["sap.m", "sap.ui.layout"],
			components : []
		},

		rootView : "test.my.fiori.view.App",

		config : {
			resourceBundle : "i18n/messageBundle.properties",
			serviceConfig : {
				name: "NorthwindModel",
				serviceUrl: "http://"+window.location.hostname+":"+window.location.port+"/hmsapi/CarService.svc/"
			}
		},

		routing : {
			config : {
				routerClass : test.my.fiori.MyRouter,
				viewType : "XML",
				viewPath : "test.my.fiori.view",
				targetAggregation : "pages",
				clearTarget : false
			},
			routes : [
				{
					pattern : "",
					name : "main",
					view : "Launchpad",
					targetAggregation : "pages",
					targetControl : "idAppControl",
					level:1,
					viewType : "XML",
					subroutes : [  ]
				},
				
				
				
			]
		}
	},

	init : function() {
		sap.ui.core.UIComponent.prototype.init.apply(this, arguments);

		var mConfig = this.getMetadata().getConfig();

		// Always use absolute paths relative to our own component
		// (relative paths will fail if running in the Fiori Launchpad)
		var oRootPath = jQuery.sap.getModulePath("test.my.fiori");

		// Set i18n model
		var i18nModel = new sap.ui.model.resource.ResourceModel({
			bundleUrl : [oRootPath, mConfig.resourceBundle].join("/")
		});
		this.setModel(i18nModel, "i18n");

		var sServiceUrl = mConfig.serviceConfig.serviceUrl;

		//This code is only needed for testing the application when there is no local proxy available
		var bIsMocked = jQuery.sap.getUriParameters().get("responderOn") === "true";
		// Start the mock server for the domain model
		if (bIsMocked) {
			this._startMockServer(sServiceUrl);
		}

		// Create and set domain model to the component
		var oModel = new sap.ui.model.odata.ODataModel(sServiceUrl, {json: true,loadMetadataAsync: true});
		oModel.setDefaultBindingMode(sap.ui.model.BindingMode.TwoWay);
		oModel.attachMetadataFailed(function(){
            this.getEventBus().publish("Component", "MetadataFailed");
		},this);
		this.setModel(oModel);

		// Set device model
		var oDeviceModel = new sap.ui.model.json.JSONModel({
			isTouch : sap.ui.Device.support.touch,
			isNoTouch : !sap.ui.Device.support.touch,
			isPhone : sap.ui.Device.system.phone,
			isNoPhone : !sap.ui.Device.system.phone,
			listMode : sap.ui.Device.system.phone ? "None" : "SingleSelectMaster",
			listItemType : sap.ui.Device.system.phone ? "Active" : "Inactive"
		});
		oDeviceModel.setDefaultBindingMode("OneWay");
		this.setModel(oDeviceModel, "device");

		this.getRouter().initialize();
	},

	_startMockServer : function (sServiceUrl) {
		jQuery.sap.require("sap.ui.core.util.MockServer");
		var oMockServer = new sap.ui.core.util.MockServer({
			rootUri: sServiceUrl
		});

		var iDelay = +(jQuery.sap.getUriParameters().get("responderDelay") || 0);
		sap.ui.core.util.MockServer.config({
			autoRespondAfter : iDelay
		});

		oMockServer.simulate("model/metadata.xml", "model/");
		oMockServer.start();

		sap.m.MessageToast.show("Running in demo mode with mock data.", {
			duration: 4000
		});
	},

	getEventBus : function () {
		return sap.ui.getCore().getEventBus();
	}
});
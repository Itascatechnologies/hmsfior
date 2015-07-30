sap.ui.jsview("test.my.fiori.view.Launchpad", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf routingdemo.Launchpad
	*/ 
	getControllerName : function() {
		return "test.my.fiori.view.Launchpad";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf routingdemo.Launchpad
	*/ 
	createContent : function(oController) {
		var tc = new sap.m.TileContainer("lauchpadTC");
		//number="{number}" numberUnit:"{numberUnit}" title:"{title}" info:"{info}"
//			infoState="{infoState}" 
//			press="handleTilePress"
		var t = sap.m.StandardTile({info:"{info}"
			
		});
		//tc.addTile(t);
		tc.bindAggregation("tiles","/Menus",t);
 		return new sap.m.Page({
			title: "Application Launchpad Page",
			content: tc
		});
	}

});
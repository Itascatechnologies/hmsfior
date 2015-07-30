//jQuery.sap.declare("test.my.fiori.FragmentFactory");

var FragmentFactory=function(){
	this._fType={
		create:"Create",
		editOnly:"Edit",
		printOnly:'Print',
		viewOnly:'Read',
		menus:"SubMenu"
	};
	this._fragmentCache=[];
	
	this._getFragmentFromCache=function(fName,type){
		var _fragments = this._fragmentCache[sFragmentName];
		for(var _f in _fragments){
			if(_fragments[_f].type==type){
				return _fragments[_f];
			}
		}
		return null;
	};
	this._setFragmentToCache=function(oFragment,sFragmentName){
		for(var _f in this._fragmentCache){
			if(this._fragmentCache[_f].name==sFragmentName){
				return this._fragmentCache[_f].fragment;
			}
		}
		return null;
	};
	this._validateFragmentType=function(fType){
		for(var t in this._fType){
			//alert(this._fType[t]+"::"+fType);
			if(this._fType[t]==fType){
				
				return true;
			}
		}
		return false;
	};
	this._getRequestedFragment=function(sFragmentName,oControl,fType){
		debugger;
		if(this._fType.menus == fType){
			var oFormFragment = this._getFragmentFromCache[fType+'_'+sFragmentName];
			var oFormFragment = this._getFragmentFromCache[sFragmentName];
			if(oFormFragment==null){
				  oFormFragment = sap.ui.xmlfragment(oControl.getId(), "test.my.fiori.fragments.menus." + fType+'_'+sFragmentName);
				  this._setFragmentToCache({fragment:oFormFragment,name:fType+'_'+sFragmentName});
			}
			 return  oFormFragment
		}else{
			if(!this._validateFragmentType(fType)){
				console.log("No fragment existance found for "+ fType);
				return null;
			}
			var oFormFragment = this._getFragmentFromCache[sFragmentName];
			if(oFormFragment==null){
				  oFormFragment = sap.ui.xmlfragment(oControl.getId(), "test.my.fiori.fragments." + sFragmentName);
				  this._setFragmentToCache({fragment:oFormFragment,name:sFragmentName});
			}
			 return  oFormFragment
		}

		
		
	};
	this._showFormFragment =function (sFragmentName,oControl) {
	    var oPage = oControl.getView().byId("detailPage");
	    oPage.removeAllContent();
	    oPage.insertContent(this._getFormFragment(sFragmentName));
	  };
	 this._destory=function(){
		 this._fragmentCache.clear();
	 };
	 this._inilized=function(){
		//
	 }
	  
}
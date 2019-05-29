package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.UploadedFile;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.constatnt.MessagesKeys;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.portal.base.BaseBean;
import com.ntgclarity.smartcompound.portal.utils.GeolocationUtils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.gmap.GMap;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
@ViewScoped
public class CompoundBean extends BaseBean implements Serializable {

	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;
	private LazyDataModel<Compound> lazyCompoundModel;
	private List<Compound> compoundList = new ArrayList<Compound>();
	private List<String> compoundNamesList = new ArrayList<String>();
	private GMap gmap;
	private String filename = "";
	private Compound selectedCompound;
	private MapModel emptyModel;
	private MapModel selectedCompoundModel;

	int zoom = 8;

	private Double lat = 0.0, lng = 0.0;
	private Marker marker;
	private UploadedFile file;

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
		getAllCompoundNames();
		initiateNewCompound();
		emptyModel = new DefaultMapModel();

		gmap = new GMap();
		selectedCompoundModel = new DefaultMapModel();
		LoadData();
	}

	public int getZoom() {
		return zoom;
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	public void getAllCompoundNames() {
		compoundList = smartCompoundManagment.getALLCompounds();
		for (Compound compound : compoundList) {
			compoundNamesList.add(compound.getCompoundName());
		}
	}

	public MapModel getSelectedCompoundModel() {
		return selectedCompoundModel;
	}

	public void setSelectedCompoundModel(MapModel selectedCompoundModel) {
		this.selectedCompoundModel = selectedCompoundModel;
	}

	public LazyDataModel<Compound> getLazyCompoundModel() {
		return lazyCompoundModel;
	}

	public void setLazyCompoundModel(LazyDataModel<Compound> lazyCompoundModel) {
		this.lazyCompoundModel = lazyCompoundModel;
	}

	public List<String> getCompoundNamesList() {
		return compoundNamesList;
	}

	public void setCompoundNamesList(List<String> compoundNamesList) {
		this.compoundNamesList = compoundNamesList;
	}

	public Marker getMarker() {
		return marker;
	}

	public MapModel getEmptyModel() {
		return emptyModel;
	}

	public UploadedFile getFile() {

		return file;
	}

	public void setFile(UploadedFile file) {

		this.file = file;
	}

	public Compound getSelectedCompound() {
		return selectedCompound;
	}

	public void setSelectedCompound(Compound selectedCompound) {
		this.selectedCompound = selectedCompound;
	}

	public void initiateNewCompound() {
		selectedCompound = new Compound();
	}

	public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}

	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
	}

	public List<Compound> getCompoundList() {
		return compoundList;
	}

	public void setCompoundList(List<Compound> compoundList) {
		this.compoundList = compoundList;
	}

	public void insertCompound() {
		
			if (!(filename.equals(""))) {
				selectedCompound.setCompoundImage(filename);
			}

			selectedCompound.setLattitude(lat);
			selectedCompound.setLongtude(lng);
			try {
			smartCompoundManagment.insertCompound(selectedCompound);
			addInfoMessage(MessagesKeys.SMART_COMPOUND_COMPOUND_PAGE_COMPOUND_INSERTION_MESSAGE);
		} catch (SmartCompoundException e) {
			
			if(e.getMessageKey().equals("SMART_COMPOUND_CHECK_EXISTENT_OF_COMPOUND_NAME")){
//				System.out.println("i m here in first if");
				FacesContext.getCurrentInstance().addMessage("createCompoundForm:compoundName",new FacesMessage(FacesMessage.SEVERITY_FATAL,"please, select another compoundName", null));
			}
			if(e.getMessageKey().equals("SMART_COMPOUND_CHECK_EXISTENT_OF_DOMAIN_NAME")){
			
				FacesContext.getCurrentInstance().addMessage("createCompoundForm:domainName",new FacesMessage(FacesMessage.SEVERITY_FATAL,"please, select another domainName", null));

			}
			RequestContext.getCurrentInstance().addCallbackParam(
					"success", false);
		}
		
	}

	public void uploadPhoto(FileUploadEvent e) throws IOException {

		UploadedFile uploadedPhoto = e.getFile();

		String filePath = "D:/";
		byte[] bytes = null;

		if (null != uploadedPhoto) {
			bytes = uploadedPhoto.getContents();
			filename = uploadedPhoto.getFileName();
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File(filePath + filename)));
			stream.write(bytes);
			stream.close();
		}

		FacesContext.getCurrentInstance().addMessage(
				"messages",
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Your Photo (File Name " + uploadedPhoto.getFileName()
								+ " with size " + uploadedPhoto.getSize()
								+ ")  Uploaded Successfully", ""));
	}

	public void updateCompound() throws SmartCompoundException {
		smartCompoundManagment.updateCompound(selectedCompound);

	}

	public void onPointSelect(PointSelectEvent event) {
		LatLng latlng = event.getLatLng();
		gmap.setZoom(zoom);
		emptyModel.getMarkers().clear();
		emptyModel.addOverlay(new Marker(latlng));
		selectedCompoundModel = emptyModel;
		lat = latlng.getLat();
		lng = latlng.getLng();
		selectedCompound.setLattitude(lat);
		selectedCompound.setLongtude(lng);
		String[] cityAndCountry = GeolocationUtils.getRestfulLocation(latlng
				.getLat() + "," + latlng.getLng());

		selectedCompound.setCity(cityAndCountry[0]);
		selectedCompound.setCountry((cityAndCountry[1]));

	}

	public void zoomLevel() {

		String zoomLevel = getRequestParam("zoomLevel");
		if (zoomLevel != null) {
			try {
				// zoom = Integer.parseInt(zoomLevel);
			} catch (Exception ex) {

			}
		}
	}

	public void initSelectedCompoundModel() {
		try{
			LatLng latlng = new LatLng(selectedCompound.getLattitude(),
					selectedCompound.getLongtude());
			selectedCompoundModel.getMarkers().clear();
			selectedCompoundModel.addOverlay(new Marker(latlng));
		}
		catch(Exception ex){
			
		}

	}

	private void LoadData() {
		lazyCompoundModel = new LazyDataModel<Compound>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private List<Compound> result;

			@Override
			public Compound getRowData(String rowKey) {
				for (Compound compound : result) {
					if (compound.getId().toString().equals(rowKey))
						return compound;
				}

				return null;
			}

			@Override
			public Object getRowKey(Compound compound) {
				return compound.getId();
			}

			@Override
			public List<Compound> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				result = getSmartCompoundManagment().loadCompounds(first,
						pageSize, sortField, sortOrder == SortOrder.ASCENDING,
						filters);
				this.setRowCount(getSmartCompoundManagment()
						.getNumOfCompoundsRows(filters));

				return result;
			}

			@Override
			public void forEach(Consumer<? super Compound> action) {
				// TODO Auto-generated method stub

			}

			@Override
			public Spliterator<Compound> spliterator() {
				// TODO Auto-generated method stub
				return null;
			}

		};

	}

	public void onStateChange(StateChangeEvent event) {
		zoom = event.getZoomLevel();
	}

	public org.primefaces.component.gmap.GMap getGmap() {
		return gmap;
	}

	public void setGmap(org.primefaces.component.gmap.GMap gmap) {
		this.gmap = gmap;
	}
	
    public void disableButton(){
    	selectedCompound=null;
    }

}

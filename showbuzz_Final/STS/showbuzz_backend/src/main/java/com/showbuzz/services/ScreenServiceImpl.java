package com.showbuzz.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.showbuzz.dao.IScreenDao;
import com.showbuzz.dao.ITheatreDao;
import com.showbuzz.dto.DtoEntityConvertor;
import com.showbuzz.dto.ScreenDTO;
import com.showbuzz.entity.Screen;
import com.showbuzz.entity.Theatre;

@Transactional
@Service
public class ScreenServiceImpl {

	@Autowired
	private IScreenDao screenDao;
	
	@Autowired
	private ITheatreDao theatreDao;
	@Autowired
	private DtoEntityConvertor convertor;

	
	public ScreenDTO findById(int id) {
		Screen screen = screenDao.findById(id);
		return convertor.toScreenDTO(screen);
	}
	
//	public Map<String , Object> saveScreen(ScreenDTO dto){
//		Screen screen = convertor.toScreenEntity(dto);
//		screenDao.save(screen);
//		return Collections.singletonMap("insertedId", screen.getScreen_id());
//	}

	public Map<String , Object> saveScreen(ScreenDTO dto){

//		int screen_no=0;
//		List<Screen> screenList = screenDao.findAll();
//		if(screenList.contains(convertor.toScreenEntity(dto))) {
//			for (Screen s : screenList) {
//				if(s.getTheatre().getId()==dto.getTheatre_id()) {
//					screen_no = s.getScreen_no();
//				}
//			}
//			dto.setScreen_no(screen_no + 1);
//		}
		
		Screen findScreen = screenDao.findByTheatreIdAndScreenNo(dto.getTheatre_id(), dto.getScreen_no());
		if(findScreen==null) {
			Screen screen = convertor.toScreenEntity(dto);
			Theatre theatre = theatreDao.findById(dto.getTheatre_id());
			theatre.addScreen(screen);
			screenDao.save(screen);
			int noOfScreens = screenDao.countNoOfScreen(dto.getTheatre_id());
			if(theatre.getNoOfScreen()<noOfScreens)
				theatre.setNoOfScreen(noOfScreens);
			return Collections.singletonMap("insertedId", screen.getScreen_id());
		}
		else
			return Collections.singletonMap("screen " +findScreen.getScreen_no() + " already present in this theatre "+findScreen.getTheatre().getName(), null);
			
	}
	
	public Map<String , Object> updateScreen(int id,ScreenDTO dto){
		if(screenDao.existsById(id)) {
			Screen updateScreen = screenDao.findById(id);
			dto.setId(id);
//			Screen screen = convertor.toScreenEntity(dto);
			updateScreen.setScreen_no(dto.getScreen_no());;
			updateScreen =screenDao.save(updateScreen);
			return Collections.singletonMap("changedRows", 1);
		}
		return Collections.singletonMap("changedRows", 0);
	}
//	
	public Map<String, Object> deleteShow(int id) {
		if(screenDao.existsById(id)) {
			screenDao.deleteById(id);
		return Collections.singletonMap("changedRows", 1);
	}
	return Collections.singletonMap("changedRows", 0);
	}
	
	public List<ScreenDTO> findAll(){
		List<Screen> screenList = screenDao.findAll();
		return screenList.stream()
				.map(s -> convertor.toScreenDTO(s))
				.collect(Collectors.toList());
	}
	
	/*	List of screen for particular Theatre	*/
	public List<ScreenDTO> findScreenByTheatreId(int theatre_id){
		List<Screen> screenList = new ArrayList<>();
		screenList = screenDao.findByTheatreId(theatre_id);
		return screenList.stream()
				.map(s -> convertor.toScreenDTO(s))
				.collect(Collectors.toList());
	}
	
	
	
}

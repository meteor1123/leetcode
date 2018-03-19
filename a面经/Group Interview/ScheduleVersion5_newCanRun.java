package schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ScheduleVersion5_newCanRun {
	public static List<List<ScheduleRequest>> schedule(List<ScheduleRequest> requests, HashMap<String, Integer> contentScoreMap, HashMap<String, Double> locationValueMap) {
		List<List<ScheduleRequest>> res = new ArrayList<>();
		if (requests == null || requests.size() == 0) {
			return res;
		}
		List<ScheduleRequest> unValidReq = new ArrayList<>();
		List<ScheduleRequest> validReq = new ArrayList<>();
		for (int i = 0; i < requests.size(); i++) {
			if (isValidTime(requests.get(i))) {
				validReq.add(requests.get(i));
			} else {
				unValidReq.add(requests.get(i));
			}
		}
		List<ScheduleRequest> validRes = new ArrayList<>();
		List<ScheduleRequest> unvalidRes = new ArrayList<>();
		Comparator<ScheduleRequest> comp = new Comparator<ScheduleRequest>() {
			@Override
			public int compare(ScheduleRequest o1, ScheduleRequest o2) {
				// TODO Auto-generated method stub
				return o1.getStartTime() - o2.getStartTime();
			}		
		};
		Collections.sort(validReq, comp);
		HashMap<String, HashMap<Integer, String>> locationMap = new HashMap<>();
		for (ScheduleRequest request : validReq) {
			String location = request.getLocation();
			String id = request.getId();
			if (!locationMap.containsKey(location)) {
				locationMap.put(location, new HashMap<Integer, String>());
				for (int i = 0; i <= 23; i++) {
					locationMap.get(location).put(i, "");
				}
			}
			int startTime = request.getStartTime();
			int endTime = request.getEndTime();
			
			if (checkTime(locationMap.get(location), startTime, endTime, id)) {
				for (int i = startTime; i <= endTime; i++) {
					locationMap.get(location).put(i, id);
				}
				validRes.add(request);
			} else {
				if (!unValidReq.contains(request)) {
					unValidReq.add(request);
				}
			}
		}
		res.add(new ArrayList<ScheduleRequest>());
		res.add(new ArrayList<ScheduleRequest>());
		res.get(0).addAll(validRes);
		res.get(1).addAll(unValidReq);
		return res;
	}
	
	public static boolean checkTime(HashMap<Integer, String> timeMap, int startTime, int endTime, String id) {
		for (int i = (startTime + 1) % 24 ; i <= endTime; i++) {
			if (!(timeMap.get(i).equals(""))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isValidTime(ScheduleRequest request) {
		if (request == null) {
			return false;
		}
		int startTime = request.getStartTime();
		int endTime = request.getEndTime();
	
		if (startTime >= endTime) { 
			return false;
		} else {
			return true;
		}
	}
}

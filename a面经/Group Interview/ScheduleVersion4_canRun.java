package schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ScheduleVersion4_canRun {
	public static List<List<ScheduleRequest>> schedule(List<ScheduleRequest> requests, HashMap<String, Integer> contentScoreMap, HashMap<String, Double> locationValueMap) {
		List<List<ScheduleRequest>> res = new ArrayList<>();
		if (requests == null || requests.size() == 0) {
			return res;
		}
		HashSet<ScheduleRequest> unValidReq = new HashSet<>();
		List<ScheduleRequest> validReq = new ArrayList<>();
		for (int i = 0; i < requests.size(); i++) {
			if (isValidTime(requests.get(i))) {
				validReq.add(requests.get(i));
			} else {
				unValidReq.add(requests.get(i));
			}
		}
		HashMap<String, List<ScheduleRequest>> locationMap = new HashMap<>();
		for (String location : locationValueMap.keySet()) {
			locationMap.put(location, new ArrayList<ScheduleRequest>());
		}
		for (ScheduleRequest request : validReq) {
			String location = request.getLocation();
			if (!locationMap.containsKey(location)) {
				continue;
			} 
			locationMap.get(location).add(request);
		}
		Comparator<ScheduleRequest> comp = new Comparator<ScheduleRequest>() {
			@Override
			public int compare(ScheduleRequest r1, ScheduleRequest r2) {
				// TODO Auto-generated method stub
				return r1.getStartTime() - r2.getStartTime();
			}
		};
		for (String location : locationMap.keySet()) {
			HashSet<String> set = new HashSet<>();
			List<ScheduleRequest> sameLocationReq = locationMap.get(location);
			Collections.sort(sameLocationReq, comp);
			int index = 0;
			ScheduleRequest pre = sameLocationReq.get(index);
			set.add(pre.getId());
			index++;
			while (index < sameLocationReq.size()) {
				ScheduleRequest cur = sameLocationReq.get(index);
				if (set.contains(cur.getId()) || !checkTwoReqTime(pre, cur)) {
					unValidReq.add(cur);
					sameLocationReq.remove(cur);
				} else {
					set.add(cur.getId());
					pre = cur;
					index++;
				} 
			}
		}
		res.add(new ArrayList<ScheduleRequest>());
		res.add(new ArrayList<ScheduleRequest>());
		for (List<ScheduleRequest> item : locationMap.values()) {
			res.get(0).addAll(item);
		}
		res.get(1).addAll(unValidReq);
		return res;
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
	public static boolean checkTwoReqTime(ScheduleRequest r1, ScheduleRequest r2) {
		return r2.getStartTime() >= r1.getEndTime();
	}
}

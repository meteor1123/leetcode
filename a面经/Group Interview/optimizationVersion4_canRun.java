package optimization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Test;

import schedule.ScheduleRequest;

public class optimizationVersion4_canRun {
	public static List<ScheduleRequest> optimize(List<ScheduleRequest> scheduleList, List<ScheduleRequest> insertList, final HashMap<String, Integer> contentScoreMap, final HashMap<String, Double> locationValueMap) {
		//if the insertList is null or size() equals zero, we dont need to optimize,just return
		if (insertList == null || insertList.size() == 0) {
			return scheduleList;
		}
		//this comparator is used to sort the ScheduleRequest list by startTime, ascending order
		Comparator<ScheduleRequest> Schedulecomp = new Comparator<ScheduleRequest>(){
			@Override
			public int compare(ScheduleRequest o1, ScheduleRequest o2) {
				// TODO Auto-generated method stub
				if (o1.getStartTime() > o2.getStartTime()) {
					return 1;
				} else if (o1.getStartTime() < o2.getStartTime()) {
					return -1;
				} else {
					return 0;
				}
			}
		};
		Comparator<ScheduleRequest> SJFcomp = new Comparator<ScheduleRequest>(){
			@Override
			public int compare(ScheduleRequest o1, ScheduleRequest o2) {
				if (o1.getLen() - o2.getLen() > 0) {
					return 1;
				} else if (o1.getLen() - o2.getLen() < 0) {
					return -1;
				} else {
					return contentScoreMap.get(o1.getId()) - contentScoreMap.get(o2.getId());
				}
			}
		};
		Comparator<ScheduleRequest> AvgWeightLencomp = new Comparator<ScheduleRequest>(){
			@Override
			public int compare(ScheduleRequest o1, ScheduleRequest o2) {
				int weight1 = contentScoreMap.get(o1.getId());
				int weight2 = contentScoreMap.get(o2.getId());
				int len1 = o1.getLen();
				int len2 = o2.getLen();
				double avgWeightLen1 = (double)weight1 / len1;
				double avgWeightLen2 = (double)weight2/ len2;
				if (avgWeightLen1 > avgWeightLen2) {
					return -1;
				} else if (avgWeightLen1 < avgWeightLen2) {
					return 1;
				} else {
					return o1.getLen() - o2.getLen();
				}
			}
		};
		Collections.sort(scheduleList, Schedulecomp);
		Collections.sort(insertList, AvgWeightLencomp);
		HashMap<String, List<ScheduleRequest>> locationMap = new HashMap<>();
		for (String location : locationValueMap.keySet()) {
			locationMap.put(location, new ArrayList<ScheduleRequest>());
		}
		for (ScheduleRequest req : scheduleList) {
			locationMap.get(req.getLocation()).add(req);
		}
		Comparator<String> LocationValuecomp = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				if (locationValueMap.get(o1) - locationValueMap.get(o2) > 0) {
					return -1;
				} else if (locationValueMap.get(o1) - locationValueMap.get(o2) < 0) {
					return 1;
				} else {
					return 0;
				}
			}
		};
		PriorityQueue<String> locationQueue = new PriorityQueue<String>(locationMap.keySet().size(), LocationValuecomp);
		for (String city : locationMap.keySet()) {
			locationQueue.offer(city);
		}
		for (int i = 0; i < insertList.size(); i++) {
			ScheduleRequest cur = insertList.get(i);
			int insertLen = cur.getLen();
			Iterator iterator = locationQueue.iterator();
			while (iterator.hasNext()) {
				String location = (String) iterator.next();
				if (insertSchedule(location, cur, locationMap)) {
					break;
				} else {
					continue;
				}
			}
		}
		 List<ScheduleRequest> res = new ArrayList<>();
		 for (List<ScheduleRequest> list : locationMap.values()) {
			 res.addAll(list);
		 }
		return res;	
	}
	public static boolean insertSchedule(String location, ScheduleRequest insertReq, HashMap<String, List<ScheduleRequest>> locationMap) {
		List<ScheduleRequest> list = locationMap.get(location);
		if (list == null) {
			return false;
		}
		if (list.size() == 0) {
			insertReq.setLocation(location);
			insertReq.setStartTime(0);
			insertReq.setEndTime(0 + insertReq.getLen());
			list.add(insertReq);
			return true;
		}
		if (list.size() == 1) {
//			insertReq.setLocation(location);
//			insertReq.setStartTime(list.get(0).getEndTime());
//			insertReq.setEndTime(list.get(0).getEndTime() + insertReq.getLen());
//			list.add(insertReq);
			return false;
		}
		Comparator<ScheduleRequest> comp = new Comparator<ScheduleRequest>(){
			@Override
			public int compare(ScheduleRequest o1, ScheduleRequest o2) {
				// TODO Auto-generated method stub
				if (o1.getStartTime() > o2.getStartTime()) {
					return 1;
				} else if (o1.getStartTime() < o2.getStartTime()) {
					return -1;
				} else {
					return 0;
				}
			}
		};
		ScheduleRequest pre = list.get(0);
		int minLen = Integer.MAX_VALUE;
		ScheduleRequest req = null;
		for (int i = 1; i < list.size(); i++) {
			ScheduleRequest cur = list.get(i);
			if (cur.getStartTime() - pre.getEndTime() >= insertReq.getLen()) {
				if (minLen >= cur.getStartTime() - pre.getEndTime()) {
					minLen = cur.getStartTime() - pre.getEndTime();
					req = pre;
				}	
			} 
			pre = cur;
		}
		if (minLen == Integer.MAX_VALUE) {
			return false;
		}
		insertReq.setLocation(location);
		insertReq.setStartTime(req.getEndTime());
		insertReq.setEndTime(req.getEndTime() + insertReq.getLen());
		locationMap.get(location).add(insertReq);
		Collections.sort(locationMap.get(location), comp);
		return true;
	}
	public static int calculateResValue(List<ScheduleRequest> res, HashMap<String, Integer> contentScoreMap, HashMap<String, Double> locationValueMap) {
		if (res == null || res.size() == 0 || contentScoreMap == null || locationValueMap == null) {
			return 0;
		}
		int value = 0;
		for (ScheduleRequest req : res) {
			value += contentScoreMap.get(req.getId()) * locationValueMap.get(req.getLocation());
		}
		return value;	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<ScheduleRequest> scheduleList = new ArrayList<>();
		scheduleList.add(new ScheduleRequest("1", "NYC", 0, 1));
		scheduleList.add(new ScheduleRequest("2", "NYC", 4, 6));
		scheduleList.add(new ScheduleRequest("3", "LA", 0, 1));
		scheduleList.add(new ScheduleRequest("4", "LA", 4, 5));
		List<ScheduleRequest> insertList = new ArrayList<>();
		insertList.add(new ScheduleRequest("5", 1));
		insertList.add(new ScheduleRequest("6", 1));
		insertList.add(new ScheduleRequest("7", 1));
		insertList.add(new ScheduleRequest("8", 3));
		HashMap<String, Integer> contentScoreMap = new HashMap<>();
		contentScoreMap.put("1", 1);
		contentScoreMap.put("2", 2);
		contentScoreMap.put("3", 3);
		contentScoreMap.put("4", 4);
		contentScoreMap.put("5", 1);
		contentScoreMap.put("6", 1);
		contentScoreMap.put("7", 1);
		contentScoreMap.put("8", 8);
		final HashMap<String, Double> locationValueMap = new HashMap<>();
		locationValueMap.put("NYC", 3.0);
		locationValueMap.put("LA", 4.0);
//		locationValueMap.put("SJ", 10.0);
		List<ScheduleRequest> res = optimize(scheduleList, insertList, contentScoreMap, locationValueMap);
		Comparator<ScheduleRequest> comp = new Comparator<ScheduleRequest>(){
			@Override
			public int compare(ScheduleRequest o1, ScheduleRequest o2) {
				// TODO Auto-generated method stub
				return o1.getLocation().compareTo(o2.getLocation());
			}
		};
		Collections.sort(res, comp);
		for (ScheduleRequest s : res) {
			System.out.println(s.toString());
		}
		System.out.println(calculateResValue(res, contentScoreMap, locationValueMap));
	}
}


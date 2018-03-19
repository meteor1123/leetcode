package selection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import schedule.ScheduleRequest;

public class selectionVersion2_dfsByLocation {
	public static List<List<String>> selection(SelectionRequest selectionReq, List<ScheduleRequest> scheduleList,  final HashMap<String, Integer> contentScoreMap, HashMap<String, Double> locationValueMap) {
		if (selectionReq == null || scheduleList == null || scheduleList.size() == 0) {
			return null;
		}
		HashMap<String, List<String>> locationMap= new HashMap<>();
		for (String location : locationValueMap.keySet()) {
			locationMap.put(location, new ArrayList<String>());
		}
		for (ScheduleRequest schedule : scheduleList) {
			String contentId = schedule.getId();
			String contentLocation = schedule.getLocation();
			locationMap.get(contentLocation).add(contentId);
		}
		HashSet<String> locationSet = new HashSet<>();
		for (ScheduleRequest content : scheduleList) {
			locationSet.add(content.getLocation());
		}
		String[] locationArr = new String[locationSet.size()];
		int index = 0;
		for (String location : locationSet) {
			locationArr[index++] = location;
		}
		List<List<String>> selectionList = new ArrayList<>();
		List<String> item = new ArrayList<>();
		List<List<String>> res = new ArrayList<>();
		dfs(0, locationArr, selectionList, item, locationMap, scheduleList);
		findMaxWeight(res, selectionList, contentScoreMap, locationValueMap, scheduleList);
		return res;
	}
	
	private static void dfs(int index, String[] locationArr, List<List<String>> selectionList, List<String> item, HashMap<String, List<String>> locationMap, List<ScheduleRequest> scheduleList){
		// TODO Auto-generated method stub
		if (index == locationArr.length) {
			for (String id : item) {
				System.out.println("Id:" +  id + " ");
			}
			System.out.println("*******************");
			selectionList.add(new ArrayList<>(item));
			return;
		}
		String location = locationArr[index];
		for (int i = 0; i < locationMap.get(location).size(); i++) {
			String contentId = locationMap.get(location).get(i);
			if (hasSameId(item, contentId)) {
				continue;
			} else {
				item.add(contentId);
				dfs(index + 1, locationArr, selectionList, item, locationMap, scheduleList);
				item.remove(item.size() - 1);
			}
		}
	}
	
	public static boolean hasSameId(List<String> item, String contentId) {
		if (item.contains(contentId)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void findMaxWeight(List<List<String>> res, List<List<String>> selectionListRes, HashMap<String, Integer> contentScoreMap, HashMap<String, Double> locationValueMap, List<ScheduleRequest> scheduleList) {
		double maxValue = 0;
		for (List<String> item : selectionListRes) {
			double sum = 0;
			for (String id : item) {
				sum += contentScoreMap.get(id) * locationValueMap.get(getLocationFromId(id, scheduleList));
				if (maxValue < sum) {
					res.clear();
					res.add(new ArrayList<String>(item));
					maxValue = sum;
				} else if (maxValue == sum) {
					res.add(new ArrayList<String>(item));
				}
			}
		}
	}
	
	public static String getLocationFromId(String id, List<ScheduleRequest> scheduleList) {
		for (ScheduleRequest schedule : scheduleList) {
			if (schedule.getId().equals(id)) {
				return schedule.getLocation();
			}
		}
		return "";
	}
	public static List<List<ScheduleRequest>> removeDuplicate(List<List<ScheduleRequest>> selectionListRes, final HashMap<String, Integer> contentScoreMap) {
		List<List<ScheduleRequest>> res = new ArrayList<>();
		for (List<ScheduleRequest> list : selectionListRes) {
			HashMap<String, ScheduleRequest> map = new HashMap<>();
			for (ScheduleRequest req : list) {
				if (!map.containsKey(req.getLocation())) {
					map.put(req.getLocation(), req);
				} else {
					if (contentScoreMap.get(req.getId()) > contentScoreMap.get(map.get(req.getLocation()).getId())) {
						map.put(req.getLocation(), req);
					}
				}
			}
			List<ScheduleRequest> newRes = new ArrayList<>();
			for (ScheduleRequest sr : map.values()) {
				newRes.add(sr);
			}
			res.add(newRes);
		}
		return res;
	}
	
//	public static void dfs(List<List<ScheduleRequest>> res, List<ScheduleRequest> item, String[] idArr, int index, HashMap<String, List<ScheduleRequest>> idScheduleMap) {
//		if (index == idArr.length) {
//			for (ScheduleRequest sr : item) {
//				System.out.println("    ID: " + sr.getId() + " Location: " + sr.getLocation());
//			}
//			Comparator<ScheduleRequest> comp = new Comparator<ScheduleRequest>() {
//				@Override
//				public int compare(ScheduleRequest s1, ScheduleRequest s2) {
//					// TODO Auto-generated method stub
//					return s1.getId().compareTo(s2.getId());
//				}
//			};
//			Collections.sort(item, comp);
//			System.out.println("******************************************");
//			res.add(new ArrayList<>(item));
//			return;
//		}
//		String contentId = idArr[index];
//		for (int i = 0; i < idScheduleMap.get(contentId).size(); i++) {
//			ScheduleRequest ad = idScheduleMap.get(contentId).get(i);
//			if (!checkDuplicate(item, ad)) {
//				continue;
//			} else {
//				item.add(ad);
//				dfs(res, item, idArr, index + 1, idScheduleMap);				
//				item.remove(item.size() - 1);
//			}			
//		}
//	}
	public static boolean checkDuplicate(List<ScheduleRequest> list, ScheduleRequest sr1) {
		for (ScheduleRequest sr : list) {
			if (sr.getId().equals(sr1.getId())) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SelectionRequest selectionReq = new SelectionRequest();
		selectionReq.setTime(2);
		List<String> locationList = new ArrayList<>();
		locationList.add("San Jose");
		locationList.add("New York");
//		locationList.add("Los Angel");
		locationList.add("Chicago");
//		locationList.add("Seattle");
//		locationList.add("Boston");
		selectionReq.setLocations(locationList);
		

		List<ScheduleRequest> scheduleReqList = new ArrayList<>();
		scheduleReqList.add(new ScheduleRequest("1", "San Jose", 2, 3));
		scheduleReqList.add(new ScheduleRequest("2", "New York", 2, 3));
		scheduleReqList.add(new ScheduleRequest("3", "Chicago", 2, 3));
		scheduleReqList.add(new ScheduleRequest("4", "Chicago", 2, 3));
		scheduleReqList.add(new ScheduleRequest("5", "New York", 2, 3));
		scheduleReqList.add(new ScheduleRequest("6", "San Jose", 2, 3));
//		scheduleReqList.add(new ScheduleRequest("3", "Seattle", 2, 3));
//		scheduleReqList.add(new ScheduleRequest("4", "Los Angel", 2, 3));


		
		HashMap<String, Integer> contentScoreMap = new HashMap<>();
		contentScoreMap.put("1", 1);
		contentScoreMap.put("2", 2);
		contentScoreMap.put("3", 3);
		contentScoreMap.put("4", 4);
		contentScoreMap.put("5", 5);
		contentScoreMap.put("6", 6);
//		contentScoreMap.put("7", 7);
//		contentScoreMap.put("8", 8);
//		contentScoreMap.put("9", 9);
		HashMap<String, Double> locationValueMap = new HashMap<>();
		locationValueMap.put("San Jose", 8.0);
		locationValueMap.put("New York", 5.0);
		locationValueMap.put("Chicago", 12.0);
//		locationValueMap.put("Boston", 5.0);
//		locationValueMap.put("Los Angel", 7.0);
//		locationValueMap.put("Seattle", 6.0);
	
		List<List<String>> res = new ArrayList<>();
		res = selectionVersion2_dfsByLocation.selection(selectionReq, scheduleReqList,  contentScoreMap, locationValueMap);
//		Comparator<ScheduleRequest> comp = new Comparator<ScheduleRequest>() {
//			@Override
//			public int compare(ScheduleRequest s1, ScheduleRequest s2) {
//				// TODO Auto-generated method stub
//				return s1.getId().compareTo(s2.getId());
//			}
//		};
		for (List<String> item : res) {
			Collections.sort(item);
			System.out.println("Selection :");
			for (String id : item) {
				System.out.println("    ID: " + id);
			}
			System.out.println("**********************************");
		}
		
	}
	
}

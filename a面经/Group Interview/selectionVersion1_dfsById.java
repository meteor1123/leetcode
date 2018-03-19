package selection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import schedule.ScheduleRequest;

public class selectionVersion1_dfsById {
	public static List<List<ScheduleRequest>> selection(SelectionRequest selectionReq, List<ScheduleRequest> scheduleList,  final HashMap<String, Integer> contentScoreMap, HashMap<String, Double> locationValueMap) {
		if (selectionReq == null || scheduleList == null || scheduleList.size() == 0) {
			return null;
		}
		HashMap<String, List<ScheduleRequest>> idScheduleMap= new HashMap<>();
		for (ScheduleRequest request : scheduleList) {
			String id = request.getId();
			if (!idScheduleMap.containsKey(id)) {
				idScheduleMap.put(id, new ArrayList<ScheduleRequest>());
			}
			idScheduleMap.get(id).add(request);
		}
		List<List<ScheduleRequest>> selectionListRes = new ArrayList<>();
		List<List<ScheduleRequest>> res = new ArrayList<>();
		List<ScheduleRequest> item = new ArrayList<>();
		int index = 0;
		
		String[] idArr = new String[idScheduleMap.keySet().size()];
		for (String id : idScheduleMap.keySet()) {
			idArr[index++] = id;
		}
		Arrays.sort(idArr);
		dfs(selectionListRes, item, idArr, 0, idScheduleMap);
		selectionListRes = removeDuplicate(selectionListRes, contentScoreMap);
		findMaxWeight(res, selectionListRes, contentScoreMap, locationValueMap);
		return res;
	}
	
	public static void findMaxWeight(List<List<ScheduleRequest>> res, List<List<ScheduleRequest>> selectionListRes, HashMap<String, Integer> contentScoreMap, HashMap<String, Double> locationValueMap) {
		double maxValue = 0;
		for (List<ScheduleRequest> scheduleReqList : selectionListRes) {
			double sum = 0;
			for (ScheduleRequest scheduleReq : scheduleReqList) {
				sum += contentScoreMap.get(scheduleReq.getId()) * locationValueMap.get(scheduleReq.getLocation());
				if (maxValue < sum) {
					res.clear();
					res.add(new ArrayList<ScheduleRequest>(scheduleReqList));
					maxValue = sum;
				} else if (maxValue == sum) {
					res.add(new ArrayList<ScheduleRequest>(scheduleReqList));
				}
			}
		}
	}
	public static List<List<ScheduleRequest>> removeDuplicate(List<List<ScheduleRequest>> selectionListRes, final HashMap<String, Integer> contentScoreMap) {
		List<List<ScheduleRequest>> res = new ArrayList<>();
		for (List<ScheduleRequest> list : selectionListRes) {
			HashMap<String, ScheduleRequest> map = new HashMap<>();
			for (ScheduleRequest req : list) {
				String location = req.getLocation();
				String id = req.getId();
				if (!map.containsKey(location)) {
					map.put(location, req);
				} else {
					if (contentScoreMap.get(id) >= contentScoreMap.get(map.get(location).getId())) {
						map.put(location, req);
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
	
	public static void dfs(List<List<ScheduleRequest>> res, List<ScheduleRequest> item, String[] idArr, int index, HashMap<String, List<ScheduleRequest>> idScheduleMap) {
		if (index == idArr.length) {
			for (ScheduleRequest sr : item) {
				System.out.println("    ID: " + sr.getId() + " Location: " + sr.getLocation());
			}
			Comparator<ScheduleRequest> comp = new Comparator<ScheduleRequest>() {
				@Override
				public int compare(ScheduleRequest s1, ScheduleRequest s2) {
					// TODO Auto-generated method stub
					return s1.getId().compareTo(s2.getId());
				}
			};
			Collections.sort(item, comp);
			System.out.println("******************************************");
			res.add(new ArrayList<>(item));
			return;
		}
		String contentId = idArr[index];
		for (int i = 0; i < idScheduleMap.get(contentId).size(); i++) {
			ScheduleRequest ad = idScheduleMap.get(contentId).get(i);
			if (!checkDuplicate(item, ad)) {
				continue;
			} else {
				item.add(ad);
				dfs(res, item, idArr, index + 1, idScheduleMap);				
				item.remove(item.size() - 1);
			}			
		}
	}
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
		locationList.add("Los Angel");
		locationList.add("Chicago");
		locationList.add("Seattle");
		locationList.add("Boston");
		selectionReq.setLocations(locationList);
		

		List<ScheduleRequest> scheduleReqList = new ArrayList<>();
		scheduleReqList.add(new ScheduleRequest("1", "San Jose", 2, 3));
		scheduleReqList.add(new ScheduleRequest("1", "Seattle", 2, 3));
		scheduleReqList.add(new ScheduleRequest("2", "Chicago", 2, 3));
		scheduleReqList.add(new ScheduleRequest("2", "San Jose", 2, 3));
//		scheduleReqList.add(new ScheduleRequest("3", "Chicago", 2, 3));
//		scheduleReqList.add(new ScheduleRequest("3", "Seattle", 2, 3));
//		scheduleReqList.add(new ScheduleRequest("3", "Seattle", 2, 3));
//		scheduleReqList.add(new ScheduleRequest("4", "Los Angel", 2, 3));
//		scheduleReqList.add(new ScheduleRequest("5", "San Jose", 2, 3));


		
		HashMap<String, Integer> contentScoreMap = new HashMap<>();
		contentScoreMap.put("1", 1);
		contentScoreMap.put("2", 1);
		contentScoreMap.put("3", 1);
		contentScoreMap.put("4", 4);
		contentScoreMap.put("5", 5);
		contentScoreMap.put("6", 6);
		contentScoreMap.put("7", 7);
		contentScoreMap.put("8", 8);
		contentScoreMap.put("9", 9);
		HashMap<String, Double> locationValueMap = new HashMap<>();
		locationValueMap.put("San Jose", 1.0);
		locationValueMap.put("New York", 1.0);
		locationValueMap.put("Chicago", 1.0);
		locationValueMap.put("Boston", 5.0);
		locationValueMap.put("Los Angel", 7.0);
		locationValueMap.put("Seattle", 6.0);
	
		List<List<ScheduleRequest>> res = new ArrayList<>();
		res = selectionVersion1_dfsById.selection(selectionReq, scheduleReqList,  contentScoreMap, locationValueMap);
		Comparator<ScheduleRequest> comp = new Comparator<ScheduleRequest>() {
			@Override
			public int compare(ScheduleRequest s1, ScheduleRequest s2) {
				// TODO Auto-generated method stub
				return s1.getId().compareTo(s2.getId());
			}
		};
		for (List<ScheduleRequest> sr : res) {
//			Collections.sort(sr, comp);
			System.out.println("Selection :");
			for (ScheduleRequest item : sr) {
				System.out.println("    ID: " + item.getId() + " Location: " + item.getLocation() + " Time: " + item.getStartTime());
			}
			System.out.println("**********************************");
		}
		
	}
	
}

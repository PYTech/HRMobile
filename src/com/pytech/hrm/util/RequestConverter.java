package com.pytech.hrm.util;

import java.util.List;

import com.pytech.hrm.models.rest.ParamPair;

public class RequestConverter {

	static public String getParamUrl(List<ParamPair> pairList) {

		String url = "?";

		for(int p = 0; p < pairList.size(); p++) {
			ParamPair thisPair = pairList.get(p);
			String key = thisPair.getKey();
			Object value = thisPair.getValue();
			if(p != pairList.size() - 1)
				url += key + "=" + value + "&";
			else {
				url += key + "=" + value;
			}
		}
		if(url.length() == 1)
			return null;
		else
			return url;
	}
}

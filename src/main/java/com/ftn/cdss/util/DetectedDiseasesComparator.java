package com.ftn.cdss.util;

import com.ftn.cdss.model.rules.FoundDisease;

import java.util.Comparator;

public class DetectedDiseasesComparator implements Comparator<FoundDisease>{

    @Override
    public int compare(FoundDisease o1, FoundDisease o2) {
        final int fulfilled1 = o1.getNumOfFulfilled();
        final int symptoms1 = o1.getNumOfSymptoms();
        final int fulfilled2 = o2.getNumOfFulfilled();
        final int symptoms2 = o2.getNumOfSymptoms();

        if(fulfilled1 == symptoms1 && fulfilled2 == symptoms2){
            return fulfilled2 - fulfilled1;
        }
        return fulfilled2/symptoms2 - fulfilled1/symptoms1;

//        if (fulfilled1 == symptoms1) {
//            if (fulfilled2 == symptoms2) {
//                if (fulfilled1 >= fulfilled2) {
//                    return -1;
//                } else {
//                    return 1;
//                }
//            }
//        }else if(fulfilled2 == symptoms2){
//            return 1;
//        }else{
//            if(fulfilled1 >= fulfilled2){
//                return -1;
//            }else{
//                return 1;
//            }
//        }
//        return 0;
    }
}

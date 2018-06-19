package com.ftn.cdss.util;

import com.ftn.cdss.model.rules.FoundDisease;

import java.util.Comparator;

public class DetectedDiseasesComparator implements Comparator<FoundDisease> {

    @Override
    public int compare(FoundDisease o1, FoundDisease o2) {
        final double fulfilled1 = o1.getNumOfFulfilled();
        final double symptoms1 = o1.getNumOfSymptoms();
        final double fulfilled2 = o2.getNumOfFulfilled();
        final double symptoms2 = o2.getNumOfSymptoms();

        if (fulfilled1 == symptoms1 && fulfilled2 == symptoms2) {
            return -(int)(fulfilled1 - fulfilled2);
        }

        if((fulfilled1 / symptoms1 - fulfilled2 / symptoms2)  >= 0){
            return -1;
        }else {
            return 1;
        }

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

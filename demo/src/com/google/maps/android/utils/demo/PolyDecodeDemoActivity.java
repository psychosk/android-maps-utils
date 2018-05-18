/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.maps.android.utils.demo;

import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.util.List;

public class PolyDecodeDemoActivity extends BaseDemoActivity {

    private final static String LINE = "y`acDkngmMN}C??d@S??DcAD_ABg@?ADq@PmDHcBJyBX{E??YYKIOGEAC?MCkSvMULy@l@oAr@a@TQHkK|FeBbAm@`@aCxAmAv@aAj@o@`@??[T[w@a@}@g@qAqC_HCGwDcJIYEOGQ]{@Sg@aCaGu@gBCEgGkOiA}CgAsCa@gA{AwD]w@k@sAiByEEKUm@oBcGw@_B_@u@w@kBm@aA??U{@Yu@q@eBm@_Bg@yAQk@Ki@E]AOEs@Aq@@{B@eD?cC@mC@[@eA@eA@sH?uH?_A@kB?{CAc@?SAM?OCQEQEQIUIOe@gAw@iBa@_ASc@gAqBs@gB{@qB}@sBcB{Dk@sAWi@Sc@MYu@}Aw@_Bm@kAM[Um@Ma@i@kBGQEQEMGKIKKMKKQKKKMGmCqAsFuDgA}@sFeEyAuAkCuCeAwAU_@OYS_@]s@c@aAoC}GwBsF[_A[aAyBgIk@eB_BqDaBqDiAkEWeAAEi@wBCQOw@Ou@Qy@Q{@_@}A[_Bk@wCm@uCOo@Q{@g@yBq@}C??SJIBUHIBI?_@@w@By@@mA@eA@gDFg@BE?CAyAc@wGqB??]~AQ~@WxACLCNMd@?D??IBKBK@QBA@_@DaFp@kBVI@oC^YD_AN{@Lm@FE@s@HyC`@_AJ???D?FAD?DAD?DADAFCHAJCHEHEHEHGHGFEBA@CBC@C@A@C@`@dAj@`BX|@n@lBBJhBhF??@Az@a@";
    //private final static LatLng POINT = new LatLng(26.880414000070328,75.73751626002208);

    private static final LatLng[] POINTS = {new LatLng(26.880414000070328,75.73751626002208),
            new LatLng(26.8797065,75.7503638),
            new LatLng(26.8900626,75.7555818),
            new LatLng(26.9026875,75.7748333),
            new LatLng(26.909612,75.7813997),
            new LatLng(26.9167862,75.7853879),
            new LatLng(26.9201592,75.7920762)};


    @Override
    protected void startDemo() {


        List<LatLng> decodedPath = PolyUtil.decode(LINE);
        getMap().addPolyline(new PolylineOptions().addAll(decodedPath));
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(POINTS[0], 12));
        for (int i = 0; i < POINTS.length; ++i){
            int result = PolyUtil.locationIndexOnEdgeOrPath(POINTS[i], decodedPath,false,false,100);
            if (result==-1){
                MarkerOptions mo = new MarkerOptions().position(POINTS[i]);
                Marker m = getMap().addMarker(mo);
                m.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            } else if (result>-1){
                MarkerOptions mo = new MarkerOptions().position(POINTS[i]);
                Marker m = getMap().addMarker(mo);
                m.setTitle(String.valueOf(result));
                m.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            }


            Toast.makeText(this,"Result is "+result,Toast.LENGTH_SHORT).show();
        }

    }
}

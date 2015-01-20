package com.vedidev.nativebridge.cocos2dx;

import android.app.Activity;

import com.vedidev.nativebridge.BunchManager;
import com.vedidev.nativebridge.JsonSerializer;
import com.vedidev.nativebridge.ProcessorEngine;
import org.cocos2dx.lib.Cocos2dxActivity;
import org.json.JSONObject;

/**
 * @author vedi
 *         date 05/10/14
 */
public class Cocos2dxGatewayAdapter {

    private static boolean contextSet = false;

    @SuppressWarnings("UnusedDeclaration")
    public static String dispatch(String strParams) {
        if (!contextSet) {
            BunchManager.getInstance().setContext(Cocos2dxActivity.getContext());
            if (Cocos2dxActivity.getContext() instanceof Activity) {
                BunchManager.getInstance().setActivity((Activity) Cocos2dxActivity.getContext());
            }
            contextSet = true;
        }

        JSONObject dictParams = JsonSerializer.deserialize(strParams);
        if (dictParams == null) {
            return null;
        }

        JSONObject retDictParams = ProcessorEngine.getInstance().proceed(dictParams);

        if (retDictParams == null) {
            return null;
        }

        return JsonSerializer.serialize(retDictParams);
    }
}
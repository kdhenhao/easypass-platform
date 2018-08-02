package cn.bluemobi.platform.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 
 * @author dingyl
 * 
 */
public class PushExample {

    protected static final Logger LOG = LoggerFactory.getLogger(PushExample.class);

    private static final String appKey = "187ee03190fd324cb87ed70b";

    private static final String masterSecret = "2adfc588d0d4ef7bec7a2aa8";

    public static final String TITLE = "easypass";

    public static String ALERT = "消息";

    public static final String MSG_CONTENT = "Test from API Example - msgContent";

    public static final String REGISTRATION_ID = "0900e8d85ef";

    public static final String TAG = "tag_api";

    public static void main(String[] args) {
        testSendPush(12l, "您有未支付的订单点击支付订单，点击查看", 1, 0);
    }

    public static void testSendPush(Long memberId, String content, long id, int type) {
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        PushPayload payload = buildPushObject_all_all_alert(memberId, content, id, type);

        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);
            System.out.println(result);

        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            System.out.println(e);

        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            System.out.println(e);
            LOG.info("HTTP Status: " + e.getStatus());
            System.out.println(e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            System.out.println(e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            System.out.println(e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
            System.out.println(e.getMsgId());
        }
    }

    public static PushPayload buildPushObject_all_all_alert(Long memberId, String content, long id, int type) {
        Map<String, String> ex = new HashMap<String, String>();
        ex.put("id", id + "");
        ex.put("type", type + "");
        ALERT = content;
        if (memberId == null) {
            return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.all())
                    .setNotification(Notification.newBuilder().setAlert(ALERT)
                            .addPlatformNotification(AndroidNotification.newBuilder().addExtras(ex).build())
                            .addPlatformNotification(IosNotification.newBuilder().addExtras(ex).build()).build())
                    .build();
        } else {
            return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(memberId + ""))
                    .setNotification(Notification.newBuilder().setAlert(ALERT)
                            .addPlatformNotification(AndroidNotification.newBuilder().addExtras(ex).build())
                            .addPlatformNotification(IosNotification.newBuilder().addExtras(ex).build()).build())
                    .build();
        }

    }
}

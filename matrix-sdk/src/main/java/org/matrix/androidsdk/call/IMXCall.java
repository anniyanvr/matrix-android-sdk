/*
 * Copyright 2015 OpenMarket Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.matrix.androidsdk.call;

import android.view.View;

import com.google.gson.JsonObject;

import org.matrix.androidsdk.data.Room;
import org.matrix.androidsdk.rest.model.Event;
import org.matrix.androidsdk.rest.model.TokensChunkResponse;
import org.matrix.androidsdk.rest.model.User;

import java.util.Collection;

/**
 * A call interface
 */
public interface IMXCall {
    // call state events
    public static final String CALL_STATE_CREATED = "IMXCall.CALL_STATE_CREATED";
    public static final String CALL_STATE_CREATING_CALL_VIEW = "IMXCall.CALL_STATE_CREATING_CALL_VIEW";
    public static final String CALL_STATE_FLEDGLING = "IMXCall.CALL_STATE_FLEDGLING";
    public static final String CALL_STATE_WAIT_LOCAL_MEDIA = "IMXCall.CALL_STATE_WAIT_LOCAL_MEDIA";
    public static final String CALL_STATE_WAIT_CREATE_OFFER = "IMXCall.CALL_STATE_WAIT_CREATE_OFFER";
    public static final String CALL_STATE_INVITE_SENT = "IMXCall.CALL_STATE_INVITE_SENT";
    public static final String CALL_STATE_RINGING = "IMXCall.CALL_STATE_RINGING";
    public static final String CALL_STATE_CREATE_ANSWER = "IMXCall.CALL_STATE_CREATE_ANSWER";
    public static final String CALL_STATE_CONNECTING = "IMXCall.CALL_STATE_CONNECTING";
    public static final String CALL_STATE_CONNECTED = "IMXCall.CALL_STATE_CONNECTED";
    public static final String CALL_STATE_ENDED = "IMXCall.CALL_STATE_ENDED";
    public static final String CALL_STATE_INVITE_EXPIRED = "IMXCall.CALL_STATE_INVITE_EXPIRED";

    // chrome type
    public static final String CHROME_CALL =  "IMXCall.CHROME_CALL";

    // call type
    public static final String CALL_TYPE_VIDEO= "IMXCall.CALL_TYPE_VIDEO";
    public static final String CALL_TYPE_AUDIO = "IMXCall.CALL_TYPE_AUDIO";

    public interface MXCallListener {
        /**
         * Called when the call state change
         */
        public void onStateDidChange(String state);

        /**
         * Called when the call fails
         */
        public void onCallError(String error);

        /**
         * The callview must be added to a layout
         * @param callview the callview
         */
        public void onViewLoading(View callview);

        /**
         * Warn when the call view is ready
         */
        public void onViewReady();

        /**
         * Warn that the call isEnded
         */
        public void onCallEnd();
    }

    // creator

    /**
     * Create the callview
     */
    public void createCallView();

    // actions (must be done after onViewReady()
    /**
     * Start a call.
     * @param isVideo true if it is a video call.
     */
    public void placeCall(Boolean isVideo);

    /**
     * Prepare a call reception.
     * @param callInviteParams the invitation Event content
     * @param callId the call ID
     */
    public void prepareIncomingCall(JsonObject callInviteParams, String callId);

    /**
     * The call has been detected as an incoming one.
     * The application launched the dedicated activity and expects to launch the incoming call.
     */
    public void launchIncomingCall();

    // events thread
    /**
     * Manage the call events.
     * @param event the call event.
     */
    public void handleCallEvent(Event event);

    // user actions
    /**
     * The call is accepted.
     */
    public void answer();

    /**
     * The call is hung up.
     */
    public void hangup();

    // listener managemenent
    public void addListener(MXCallListener callListener);
    public void removeListener(MXCallListener callListener);

    // getters / setters
    /**
     * @return the callId
     */
    public String callId();

    /**
     * Set the callId
     */
    public void setCallId(String callId);

    /**
     * @return the linked room
     */
    public Room room();

    /**
     * Set the linked room.
     * @param room the room
     */
    public void setRoom(Room room);

    /**
     * @return true if the call is an incoming call.
     */
    public Boolean isIncoming();

    /**
     * @return the callstate (must be a CALL_STATE_XX value)
     */
    public String callState();

    /**
     * @return the callView
     */
    public View callView();
}

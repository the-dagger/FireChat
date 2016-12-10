/**
 * Copyright Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dagger.chatapp;

import android.os.Parcel;
import android.os.Parcelable;

public class FriendlyMessage implements Parcelable{

    private String text;
    private String name;
    private String photoUrl;

    public FriendlyMessage() {
    }

    public FriendlyMessage(String text, String name, String photoUrl) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    protected FriendlyMessage(Parcel in) {
        text = in.readString();
        name = in.readString();
        photoUrl = in.readString();
    }

    public static final Creator<FriendlyMessage> CREATOR = new Creator<FriendlyMessage>() {
        @Override
        public FriendlyMessage createFromParcel(Parcel in) {
            return new FriendlyMessage(in);
        }

        @Override
        public FriendlyMessage[] newArray(int size) {
            return new FriendlyMessage[size];
        }
    };

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(text);
        parcel.writeString(name);
        parcel.writeString(photoUrl);
    }
}

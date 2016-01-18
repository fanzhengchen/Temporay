package com.hcb.jingle.loader.user;

import com.hcb.jingle.loader.base.BasePostLoader;
import com.hcb.jingle.model.ProfileOutBody;
import com.hcb.jingle.model.base.InBody;

public class ProfileUploader extends BasePostLoader<ProfileOutBody, InBody> {

    private final static String PATH = "user/%s/change_info";

    public void upload(final ProfileOutBody outBody, final RespReactor reactor) {
        super.load(genUrl(PATH, curUser.getUid()), outBody, reactor);
    }

}

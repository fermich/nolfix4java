package com.fermich.nolfix.fix.msg;

import com.fermich.nolfix.fix.msg.access.UserRequest;
import com.fermich.nolfix.fix.msg.access.UserResponse;
import com.fermich.nolfix.fix.msg.utils.FileUtils;
import com.fermich.nolfix.fix.msg.utils.MsgUtils;
import com.fermich.nolfix.fix.packet.FixmlMarshaller;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class AccessTest {

    private FileUtils fu;
    private FixmlMarshaller marshaller;

    @Before
    public void setUp() {
        fu = new FileUtils();
        marshaller = new FixmlMarshaller();
        marshaller.configure();
    }

    @Test
    public void testCreatingUserRequest() throws IOException {
        UserRequest userRequest = new UserRequest()
                .setUserReqId("22")
                .setUserReqTyp(UserRequest.UserRequestType.LOGIN.getUserReqTyp())
                .setUsername("Comarch")
                .setPassword("Comarch");

        Fixml fixml = userRequest.pack();
        String out = marshaller.serialize(fixml);

        Assert.assertTrue(MsgUtils.compareIgnoringWhiteSpaces(out, fu.readFile("UserReq.xml")));
    }

    @Test
    public void testUserResponse() throws IOException {
        Fixml msg = marshaller.deserialize(fu.readFile("UserRsp.xml"));
        UserResponse userResponse = msg.getUserResponses().get(0);

        Assert.assertEquals(userResponse.getUserReqId(), "22");
        Assert.assertEquals(userResponse.getUsername(), "Comarch");
        Assert.assertEquals(userResponse.getUserStat().toString(), "1");
    }
}

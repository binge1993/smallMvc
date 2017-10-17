package com.binge.smallmvc.state;

/**
 * @author binge
 * @datetime 2017年10月17日
 * @version
 * @encoding UTF8
 * @Description
 */

public class ConnectState implements State {

    @Override
    public void connect(Context c) {
        c.setState(new ConnectState());
    }

    @Override
    public void register(Context c) {
        c.setState(new RegistingState());

    }

    @Override
    public void registerSuccess(Context c) {
        throw new RuntimeException("INVALID_OPERATE_ERROR");
    }

    @Override
    public void registerFailed(Context c) {
        throw new RuntimeException("INVALID_OPERATE_ERROR");
    }

    @Override
    public void unRegister(Context c) {
        c.setState(new UnconnectState());
    }

    @Override
    public String getCurState() {
        return StateEnum.CONNECT.toString();
    }
}

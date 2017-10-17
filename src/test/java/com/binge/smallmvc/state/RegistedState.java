package com.binge.smallmvc.state;

/**
 * @author binge
 * @datetime 2017年10月17日
 * @version
 * @encoding UTF8
 * @Description
 */

public class RegistedState implements State {

    @Override
    public void connect(Context c) {
        throw new RuntimeException("INVALID_OPERATE_ERROR");
    }

    @Override
    public void register(Context c) {
        throw new RuntimeException("INVALID_OPERATE_ERROR");
    }

    @Override
    public void registerSuccess(Context c) {
        c.setState(new RegistedState());
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
        return StateEnum.REGISTED.toString();
    }

}

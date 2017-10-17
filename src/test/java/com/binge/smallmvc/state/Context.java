package com.binge.smallmvc.state;

/**
 * @author binge
 * @datetime 2017年10月17日
 * @version
 * @encoding UTF8
 * @Description
 */

public class Context {
    private State state;

    public Context() {
        setState(new UnconnectState());
        System.out.println("STATE : " + state.getCurState());
    }

    public void connect() {
        state.connect(this);
        System.out.println("STATE : " + state.getCurState());

    }

    public void register() {
        state.register(this);
        System.out.println("STATE : " + state.getCurState());

    }

    public void registerSuccess() {
        state.registerSuccess(this);
        System.out.println("STATE : " + state.getCurState());

    }

    public void registerFailed() {
        state.registerFailed(this);
        System.out.println("STATE : " + state.getCurState());

    }

    public void unRegister() {
        state.unRegister(this);
        System.out.println("STATE : " + state.getCurState());
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

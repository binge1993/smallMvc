package com.binge.smallmvc.state;

/**
 * @author binge
 * @datetime 2017年10月17日
 * @version
 * @encoding UTF8
 * @Description 登录状态机设计
 */

public interface State {

    void connect(Context c);

    void register(Context c);

    void registerSuccess(Context c);

    void registerFailed(Context c);

    void unRegister(Context c);

    String getCurState();
}

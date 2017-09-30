package com.binge.smallmvc;

import com.binge.smallmvc.helper.AopHelper;
import com.binge.smallmvc.helper.BeanHelper;
import com.binge.smallmvc.helper.ClassHelper;
import com.binge.smallmvc.helper.ControllerHelper;
import com.binge.smallmvc.helper.DruidDatabaseHelper;
import com.binge.smallmvc.helper.IocHelper;
import com.binge.smallmvc.util.ClassUtil;

/**
 * @author binge
 * @datetime 2017年9月28日
 * @version
 * @encoding UTF8
 * @Description 加载相应的 Helper 类
 */

public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = { ClassHelper.class, BeanHelper.class, AopHelper.class, IocHelper.class,
                ControllerHelper.class, DruidDatabaseHelper.class };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }

    }

}

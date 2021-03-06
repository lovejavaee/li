package li.mvc;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import li.dao.Page;
import li.model.Action;
import li.util.Convert;

/**
 * Action基类,你的Action类可以继承这个类
 * 
 * @author li (limingwei@mail.com)
 * @version 0.1.1 (2012-07-20)
 * @see li.mvc.Ctx
 */
public abstract class AbstractAction implements RequestMethod {
    /**
     * 得到Action引用
     */
    public Action getAction() {
        return Ctx.getAction();
    }

    /**
     * 返回request引用
     */
    public HttpServletRequest getRequest() {
        return Ctx.getRequest();
    }

    /**
     * 返回response引用
     */
    public HttpServletResponse getResponse() {
        return Ctx.getResponse();
    }

    /**
     * 返回ServletContext
     */
    public ServletContext getServletContext() {
        return Ctx.getServletContext();
    }

    /**
     * 返回Session引用
     */
    public HttpSession getSession() {
        return Ctx.getSession();
    }

    /**
     * 得到Session中键为key的值
     */
    public Object getSession(String key) {
        return Ctx.getSession().getAttribute(key);
    }

    /**
     * 从 request的 parameters中得到数据组装成一个 type类型的对象
     */
    public <T> T get(Class<T> type, String key) {
        return Ctx.get(type, key);
    }

    /**
     * 返回基本类型数组参数
     */
    public <T> T[] getArray(Class<T> type, String key) {
        return Ctx.getArray(type, key);
    }

    /**
     * 根据parameters中的页码参数构建一个Page,或者返回一个默认的Page
     */
    public Page getPage(String pageNumberKey) {
        return Ctx.getPage(pageNumberKey);
    }

    /**
     * 返回指定key的parameters参数
     */
    public String getParameter(String key) {
        return Ctx.getRequest().getParameter(key);
    }

    /**
     * 返回转换为特定类型的parameters参数
     */
    public <C> C getParameter(Class<C> type, String key) {
        return Convert.toType(type, getParameter(key));
    }

    /**
     * 返回项目文件系统跟路径
     */
    public String getRootPath() {
        return Ctx.getRootPath();
    }

    /**
     * 返回项目HTTP根路径
     */
    public String getRootUrl() {
        return Ctx.getRootUrl();
    }

    /**
     * 将QueryString中对应key的参数设置到request里面
     */
    public AbstractAction keepParams(String... keys) {
        Ctx.keepParams(keys);
        return this;
    }

    /**
     * 移除一个Session
     */
    public AbstractAction removeSession(String key) {
        Ctx.getSession().removeAttribute(key);
        return this;
    }

    /**
     * 向request中设值
     */
    public AbstractAction setRequest(String key, Object value) {
        Ctx.getRequest().setAttribute(key, value);
        return this;
    }

    /**
     * 向session中设值
     */
    public AbstractAction setSession(String key, Object value) {
        Ctx.getSession().setAttribute(key, value);
        return this;
    }

    /**
     * 主视图方法,以冒号分割前缀表示视图类型
     * 
     * @see li.mvc.Context#view(String)
     */
    public String view(String path) {
        return Ctx.view(path);
    }

    /**
     * 执行客户端跳转
     */
    public String redirect(String path) {
        return Ctx.redirect(path);
    }

    /**
     * 返回JSP视图
     */
    public String forward(String path) {
        return Ctx.forward(path);
    }

    /**
     * 返回Velocity视图
     */
    public String velocity(String path) {
        return Ctx.velocity(path);
    }

    /**
     * 返回Freemarker视图
     */
    public String freemarker(String path) {
        return Ctx.freemarker(path);
    }

    /**
     * 返回beetl视图
     */
    public String beetl(String path) {
        return Ctx.beetl(path);
    }

    /**
     * 把 content写到页面上
     */
    public AbstractAction write(Object content) {
        Ctx.write(content);
        return this;
    }

    /**
     * 上传文件
     */
    public AbstractAction upload(String uploadPath) {
        Ctx.upload(uploadPath);
        return this;
    }
}
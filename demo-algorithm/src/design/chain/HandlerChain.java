package design.chain;

import design.inter.Handler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.chain
 * @descripation 责任链
 * @date 2021-07-21
 */
public class HandlerChain {
    private List<Handler> list = new ArrayList<>();

   /**
    * 责任链添加责任
    * @param handler Handler
    */
   public void addHandler(Handler handler){
       list.add(handler);
   }

   /**
    * TODD
    * @param request Request
    * @return 处理结果
    */
   public boolean process(Request request){
       for(Handler handler:list){
          Boolean r = handler.process(request);
           if(null != r){
               System.out.println("最终处理的责任class： "+ handler.getClass().getSimpleName() + "， 审批结果是："+ r);
               return r;
           }
       }
       throw new RuntimeException("无法处理request");
   }
}

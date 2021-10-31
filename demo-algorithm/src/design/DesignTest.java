package design;

import design.adapter.Adapter;
import design.adapter.Callable;
import design.adapter.Task;
import design.bridging.BossCar;
import design.bridging.HybridEngine;
import design.bridging.RefinedCar;
import design.builder.UrlBuilder;
import design.chain.*;
import design.command.*;
import design.copy.Student;
import design.decorator.BoldDecorator;
import design.decorator.DelDecorator;
import design.decorator.SpanTextNode;
import design.facade.Facade;
import design.facade.OperateBank;
import design.factory.FactoryMode;
import design.flyweight.FlyWeight;
import design.inter.AbstractFactory;
import design.inter.Bank;
import design.inter.Command;
import design.inter.Node;
import design.interpreter.LogInterpreter;
import design.iterator.ReverseArrayCollection;
import design.node.CommentNode;
import design.node.ElementNode;
import design.node.TextNode;
import design.observer.CustomerObserver;
import design.observer.Product;
import design.observer.Store;
import design.proxy.BankProxy;
import design.singleton.SingleEnum;
import design.singleton.Singleton;
import design.strategy.DiscountContext;
import design.strategy.PrimeDiscountStrategy;
import design.template.AbstractSetTemplate;
import design.template.MapSetTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * @author zhangwei
 * @version 1.0
 * @className design
 * @descripation
 * @date 2021-07-16
 */
public class DesignTest {


    public static void main(String[] args) throws CloneNotSupportedException {
        //工厂模式(静态工厂)
        System.out.println("工厂模式//////////////////////");
        System.out.println(FactoryMode.fromInt(20200202));
        //抽象工厂
        System.out.println("抽象工厂//////////////////////");
        System.out.println(AbstractFactory.createFactory("good").createWord().save("抽象工厂，生成产品word"));
        //生成器
        System.out.println("生成器//////////////////////");
        Map<String,Object> map = new HashMap<>();
        map.put("page", "1");
        map.put("size", "10");
        String build = UrlBuilder.builder()
                .setScheme("https://")
                .setDomain("www.baidu.com")
                .setPath("movie")
                .setPath("get")
                .setQuery(map)
                .build();
        System.out.println(build);

       //原型
        System.out.println("原型//////////////////////");
        Student student = new Student();
        student.setId(1);
        student.setName("test1");
        student.setScore(0);
        Student student2 = (Student) student.clone();

        student2.setId(2);
        student2.setName("test2");
        student2.setScore(100);

        System.out.println("student is : "+ student);
        System.out.println("student2 is : "+ student2);

        //单例模式
        System.out.println("单例//////////////////////");
        Singleton.getInstance().setName("singleton test");
        System.out.println(Singleton.getInstance().getName());
        System.out.println(SingleEnum.INSTANCE.getName());



        //适配器
        System.out.println("适配器//////////////////////");
        Callable callable = new Task(300L);
        Thread thread = new Thread(new Adapter(callable));
        thread.start();

        //桥接
        System.out.println("桥接//////////////////////");
        RefinedCar refinedCar = new BossCar(new HybridEngine());
        refinedCar.drive();

        //组合
        System.out.println("组合//////////////////////");
        Node root = new ElementNode("xml");
        root.add(new CommentNode("学生信息展示"))
                .add(new ElementNode("student")
                        .add(new TextNode("Tom"))
                        .add(new TextNode("Alice")));

        root.add(new CommentNode("老师信息展示"))
                .add(new ElementNode("teacher")
                        .add(new TextNode("Liu"))
                        .add(new TextNode("Zhang")));
        System.out.println(root.toXml());

        //装饰器
        System.out.println("装饰器//////////////////////");
        design.inter.TextNode boldNode = new BoldDecorator(new SpanTextNode());
        boldNode.setText("黑体");
        design.inter.TextNode delNode = new DelDecorator(new BoldDecorator(new SpanTextNode()));
        delNode.setText("删除-黑体");
        System.out.println("boldDecorator : "+ boldNode.getText());
        System.out.println("delDecorator : "+ delNode.getText());

        //外观
        System.out.println("外观//////////////////////");
        System.out.println("facade : "+ new Facade().openCompany("开办公司"));

        //享元
        System.out.println("享元//////////////////////");
        System.out.println("flayWeight1 : "+ FlyWeight.create("1","测试"));
        System.out.println("享元-----------");
        System.out.println("flayWeight2 : "+ FlyWeight.create("1","测试"));

        //代理
        System.out.println("代理//////////////////////");
        Bank bank = new BankProxy(new OperateBank());
        bank.openAccount("3");


        //责任链(拦截器)
        System.out.println("责任链//////////////////////");
        HandlerChain chain = new HandlerChain();
        chain.addHandler(new ManagerHandler());
        chain.addHandler(new DirectorHandler());
        chain.addHandler(new CeoHandler());
        chain.process(new Request("manager",BigDecimal.valueOf(123)));
        chain.process(new Request("manager",BigDecimal.valueOf(1235.1)));
        chain.process(new Request("dirSome",BigDecimal.valueOf(1269.1)));
        chain.process(new Request("dirSome",BigDecimal.valueOf(131269.1)));

        //命令
        System.out.println("命令//////////////////////");
        TextEditor editor = new TextEditor();
        Command addCommand = new AddCommand(editor);
        Command copyCommand = new CopyCommand(editor);
        Command pateCommand = new PasteCommand(editor);
        Invoker invoker = new Invoker();
        invoker.addCommands(addCommand);
        invoker.addCommands(copyCommand);
        invoker.addCommands(pateCommand);
        invoker.execute();
        System.out.println("command is : "+ editor.getState());

        //解释器
        System.out.println("解释器//////////////////////");
        LogInterpreter.log("[{}] start {} at {}...", LocalTime.now().withNano(0), "engine", LocalDate.now());

        //迭代器
        System.out.println("迭代器//////////////////////");
        ReverseArrayCollection<String> collections = new ReverseArrayCollection<>(new String[]{"1","2","3","4","5","6","7"});
        for(Iterator<String> iterator = collections.iterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }

        //中介(调停者) mvc
        System.out.println("中介//////////////////////");

        //备忘录
        System.out.println("备忘录//////////////////////");
        design.memento.TextEditor textEditor = new design.memento.TextEditor();
        textEditor.add("备忘录相关状态");
        System.out.println("当前状态：" +textEditor.getState());

        textEditor.setState("修改备忘录状态");
        System.out.println("当前状态：" +textEditor.getState());

        //观察者
        System.out.println("观察者//////////////////////");
        Store store = new Store();
        store.addObserver(new CustomerObserver());
        store.addNewProduct(new Product("产品1","23.5"));
        store.addNewProduct(new Product("产品2","28.7"));
        store.addNewProduct(new Product("产品3","33.2"));
        store.setProductPrice(new Product("产品1","145.5"));

        //策略
        System.out.println("策略//////////////////////");
        DiscountContext context = new DiscountContext();
        context.setDiscountStrategy(new PrimeDiscountStrategy());
        System.out.println("策略" + context.finalCount(new BigDecimal("126.89")));
        //模板
        System.out.println("模板//////////////////////");
        AbstractSetTemplate template = new MapSetTemplate();
        template.getString("模板");

        //访问者
        System.out.println("访问者//////////////////////");
    }

}

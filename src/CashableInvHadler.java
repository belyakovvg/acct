import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class CashableInvHadler implements InvocationHandler {

    CashableInvHadler(Object obj){
        this.obj = obj;
        this.mutators = new HashMap<>();
        this.cash = new HashMap<>();
    }
    private Object obj;

    private HashMap<String, Integer> mutators; // счетчики обращений к кэшу
    private HashMap<String, Object>  cash; // кэш для кэшируемых методов

    public Integer getMutator(String s) {
       return mutators.get(s);
    }
    public HashMap<String, Integer> getMutators() {
        return mutators;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //System.out.println("It works");
        Method m = obj.getClass().getMethod(method.getName(), method.getParameterTypes());

        boolean isMutator= m.isAnnotationPresent(Mutator.class);
        boolean isCash = m.isAnnotationPresent(Cashe.class);

        if (isMutator) { //метод-мутатор
        //    System.out.println("-------mutator!");
            mutators.clear(); // кеш устарел, чистим счетчики обращения к кэшу для каждого метода
            return method.invoke(obj, args);
        };
        //метод кешируемый
        if (isCash) {
         //   System.out.println("-------CashAble!");
            if(!mutators.containsKey(m.getName().toString())){
                // кэш протух
                mutators.put(m.getName().toString(), 0);  //счетчик  обращений к кэшу
                cash.put(m.getName().toString(), method.invoke(obj,args));
                System.out.print(mutators.get(m.getName().toString())); // счетчик обращений к кэшу для тестирования
                return method.invoke(obj,args);
            } else{
                // кэш актуален
                mutators.put(m.getName().toString(),mutators.get(m.getName().toString()) + 1); // счетчик обращений к кэшу
                System.out.print(mutators.get(m.getName().toString()));
                if(cash.get(m.getName().toString()) instanceof Boolean) return  (Boolean)cash.get(m.getName().toString());
                else if(cash.get(m.getName().toString()) instanceof Double) return  (Double)cash.get(m.getName().toString());
                else if(cash.get(m.getName().toString()) instanceof Integer) return  (Integer)cash.get(m.getName().toString());
            }
        }
        //метод не мутатор и не кэшируемый
        return method.invoke(obj, args);
    }
}
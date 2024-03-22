import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class CashableInvHadler implements InvocationHandler {

    CashableInvHadler(Object obj){
        this.obj = obj;
        this.mutators =new HashMap<>();
        this.boolCash=new HashMap<>();
        this.doubleCash=new HashMap<>();
        this.cash=new HashMap<>();
    }
    private Object obj;
    private HashMap<String, Boolean> mutators;
    private HashMap<String, Boolean> boolCash;
    private HashMap<String, Double> doubleCash;
    private HashMap<String,Object>  cash;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //System.out.println("It works");
        Method m = obj.getClass().getMethod(method.getName(), method.getParameterTypes());

        boolean isMutator= m.isAnnotationPresent(Mutator.class);
        boolean isCash = m.isAnnotationPresent(Cashe.class);

        if (isMutator) { //метод мутатор
         //   System.out.println("-------mutator!");
            // поднимаем флаги "кеш устарел" для каждого метода
            for (Method f : obj.getClass().getDeclaredMethods())
            {
                if(f.isAnnotationPresent(Cashe.class))
                {
                  mutators.put(f.getName().toString(),true);

//                  System.out.println(f.getGenericReturnType());
//                  mutators.put( f.getGenericReturnType().toString(),true);
              }

            }
            System.out.println("mutators " + mutators);
                return method.invoke(obj, args);

        }
        //метод кешируемый
        if (isCash) {
         //   System.out.println("-------CashAble!");
            if(mutators.containsKey(m.getName().toString()) && mutators.get(m.getName().toString())==true){
                // кэш протух

                cash.put(m.getName().toString(), method.invoke(obj,args));
            //                if(m.getGenericReturnType()==double.class) {
//                    double res = (double)method.invoke(obj, args);
//                    doubleCash.put(m.getName().toString(), res);
//                }
//                if(m.getGenericReturnType()==boolean.class) {
//                    boolean res = (boolean)method.invoke(obj, args);
//                    boolCash.put(m.getName().toString(), res);
//                }
                System.out.println("not cash ");
                mutators.remove(m.getName().toString());
                return method.invoke(obj,args);
            }
            else{
                // кэш актуален
                System.out.println("from cashe");
                if(cash.get(m.getName().toString()) instanceof Boolean) return  (Boolean)cash.get(m.getName().toString());
                else if(cash.get(m.getName().toString()) instanceof Double) return  (Double)cash.get(m.getName().toString());
                else if(cash.get(m.getName().toString()) instanceof Integer) return  (Integer)cash.get(m.getName().toString());
//                if(m.getGenericReturnType()==double.class) {return doubleCash.get(m.getName());}
//                if(m.getGenericReturnType()==boolean.class) {return boolCash.get(m.getName());}
            }
        }
        //метод не мутатор и не кэшируемый
        return method.invoke(obj, args);
    }
}
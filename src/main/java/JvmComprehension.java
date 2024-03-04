
public class JvmComprehension { // класс JvmComprehension подгружается в Application Classloader
    public static void main(String[] args) {
        int i = 1;                      // на стеке выделяется память для переменной i, куда помещается '1' (во фрейме main())
        Object o = new Object();        // на стеке выделяется память для ссылки 'o', в куче создаётся объект Object(), ссылка 'o' из стека ссылается на Object() в куче
        Integer ii = 2;                 // на стеке выделяется память для ссылки 'ii' в куче создаётся объект Integer со значением '2', ссылка 'ii' из стека ссылается на Integer(2) в куче
        printAll(o, i, ii);             // на стеке создается фрейм printAll(), в который копируются и передаются i, ii, o
        System.out.println("finished"); // класс System подгружается в Bootstrap Classloader в самом начале выполнения программы
                                        // на стеке создается новый фрейм, в который передается объект String со значением "finished"
    }

    private static void printAll(Object o, int i, Integer ii) {
        Integer uselessVar = 700;                   // на стеке выделяется память для ссылки 'ii' в куче создаётся объект Integer со значением '700', ссылка 'ii' из стека ссылается на Integer(700) в куче
        // объект Integer(700) в куче на который ссылается uselessVar вероятней всего будет удалён GC-ом при следующем проходе GC-а
        System.out.println(o.toString() + i + ii);  // на стеке выделяется память для нового значения, которое вернет o.toString, затем o.toString + i, затем o.toString() + i + ii (если исходить из концепции неизменняемости строк)
        // временные строки при конкатенации как например "o.toString() + i" также будут вероятно удалены GC-ом в следующую его проверку
    }
}
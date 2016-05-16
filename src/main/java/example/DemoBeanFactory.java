package example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoBeanFactory {

	public static List<DemoBean> list() {

		List<DemoBean> list = new ArrayList<DemoBean>();

		AdditionalBean inner1 = new AdditionalBean("inner 1", 1);
		AdditionalBean inner2 = new AdditionalBean("inner 2", 2);
		AdditionalBean inner3 = new AdditionalBean("inner 3", 3);
		DemoBean bean1 = new DemoBean("John", "value 1", Arrays.asList(inner1, inner3, inner2));

		AdditionalBean inner4 = new AdditionalBean("inner 2", 10);
		AdditionalBean inner5 = new AdditionalBean("inner 3", 20);
		AdditionalBean inner6 = new AdditionalBean("inner 4", 30);
		DemoBean bean2 = new DemoBean("Bob", "value 2", Arrays.asList(inner6, inner4, inner5));

		AdditionalBean inner7 = new AdditionalBean("inner 4", 100);
		AdditionalBean inner8 = new AdditionalBean("inner 5", 200);
		AdditionalBean inner9 = new AdditionalBean("inner 0", 300);
		DemoBean bean3 = new DemoBean("Ken", "value 3", Arrays.asList(inner9, inner8, inner7));

		AdditionalBean inner10 = new AdditionalBean("inner 1", 1000);
		AdditionalBean inner11 = new AdditionalBean("inner 3", 2000);
		AdditionalBean inner12 = new AdditionalBean("inner 5", 3000);
		DemoBean bean4 = new DemoBean("Lisa", "value 4", Arrays.asList(inner10, inner11, inner12));

		list.add(bean3);
		list.add(bean1);
		list.add(bean2);
		list.add(bean4);

		return list;
	}

}

package com.jae.prj05.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // �޸𸮿� new�� �ø��°� ������(������ �����̳ʰ� ����) -> IoC  
public class TestController {
	
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello sprin</h1>";
	}
	
// ������ IoC(�����ǿ���) - ���� new�� �޸𸮿� �ø��� ��
//		=> '�̱���' -> ���۷��� ������ �������� ����

//		�Ŀ� ������ �Ұ͵�...
//		(������������) => new�� heap�� �ö�(������ �ּҰ����� �ּҸ�)
//		stack�� ��� ��ü�� ���������� ����? 
//		=> new�� heap�� ��� �ø��°� �ʰ� ��������
//			spring�� ��� �ϴ� �ϰ���(IoC)
//	 	������ Scan �����ϱ����� ������ �̷������͵���. 
//		https://www.youtube.com/watch?v=n33ao_cbhsU&list=PL93mKxaRDidECgjOBjPgI3Dyo8ka6Ilqm&index=6
	
	
}
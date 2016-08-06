package local.costanzo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {
	
	@RequestMapping("/helloWorld")
	public String helloWorld() {
		return "Hello world!";
	}
	
	@RequestMapping("/lambda/")
	public List<Integer> lambda() {
		return lambda(0,100);
	}
	
	@RequestMapping("/lambda/{start}/{count}")
	public List<Integer> lambda(@PathVariable("start") int start, @PathVariable("count") int count) {
		return lambdaStream(start, count).collect(Collectors.toList());
	}
	
	@RequestMapping("/lambdaStr/{start}/{count}")
	public String lambdaString(@PathVariable("start") int start, @PathVariable("count") int count) {
		return lambdaString(start, count, "\n");
	}
	
	@RequestMapping("/lambdaStr/{start}/{count}/{delim}")
	public String lambdaString(@PathVariable("start") int start, @PathVariable("count") int count, @PathVariable("delim") String delim) {
		return lambdaStream(start, count).map(String::valueOf).collect(Collectors.joining(delim));
	}
	
	@RequestMapping("/lambdaFirst/{start}/{count}")
	public int lambdaFirst(@PathVariable("start") int start, @PathVariable("count") int count) {
		return lambdaStream(start, count).findFirst().orElse(-1);
	}
	
	Stream<Integer> lambdaStream(int start, int count) {
		return Stream.iterate(start, i -> i + 1)
				.filter(MyRestController::divisibleByFive)
				.limit(count)
				.peek(System.out::println);
	}
	
	public static Boolean divisibleByFive(int num) {
		return (num % 5 == 0);
	}

}

# Springboot-JPA-blog
나만의 블로그 만들기.

# 1. 개발 환경
  * 스프링 부트 2.3.4
  * 스프링 시큐리티
  * JPA
  * maria db

# 2. Controller 단.
  ## 2.1 BoardApiController
  	```
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principal)
	{
		boardService.글쓰기(board,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
```
  ## 2.2 UserApiController
  ## 2.3 BoardController
  ## 2.4 UserController

# 3. Model
  ## 3.1 Board
  ## 3.2 User
  ## 3.3 reply
  

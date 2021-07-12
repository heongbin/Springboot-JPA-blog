# Springboot-JPA-blog
나만의 블로그 만들기.

# 1. 개발 환경
  * 스프링 부트 2.3.4
  * 스프링 시큐리티
  * JPA
  * maria db
# 2.실행 방법.
	

# 3. Controller 단.
  ## 3.1 BoardApiController
* 게시글 등록을 위한 save함수.
* 게시글 수정을 위한 update함수.
* 게시글 delete를 위한 deletedbyid함수.
	
모든 함수는 responsedto를 리턴해줌.
	
  ##responsedto
		```
		*public class ResponseDto<T> {
		int status;
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public T getData() {
			return data;
		}
		public ResponseDto(int status, T data) {
			this.status = status;
			this.data = data;
		}
		public void setData(T data) {
			this.data = data;
		}
		T data;
			}

		```	
		* status로 httpstatus의 성공 여부를 받음.
	
  ## 3.2 UserApiController
       *회원 가입,수정,탈퇴를 담당하는 controller.
  ## 3.3 BoardController
       *게시글과 댓글에 관련된 페이지를 이동해주는 controller
  ## 3.4 UserController
       *회원가입,로그인에 관한 페이지를 이동해주는 controller

# 4. Model
  ## 4.1 Board
	*@Entity//클래스가 mysql테이블이 자동생성.
	```
	public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false,length = 100)
	private String title;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCount() {
		return count;
	}

	@ManyToOne(fetch = FetchType.EAGER) //many = board -> user = one
	@JoinColumn(name="userId")//db에서는 forignkey
	private User user; 
	
	@OneToMany(mappedBy = "board",fetch=FetchType.EAGER,cascade = CascadeType.REMOVE)//mappedBy는 주인관계 표시. board가 주인이기때문에 reply가 board를 포린키로 갖음. reply클래스가 갖고잇는 아이디명.
	@JsonIgnoreProperties({"board"})
	@javax.persistence.OrderBy("id desc")
	private List<Reply> replys;
	```
*게시글 마다 id, 글제목 (title), 글내용(content), 조회수(count)
*jpa를 @entitiy로 객체에 대응되는 테이블을 자동으로 생성.
*게시글은 유저하나당 여러개를 가지고 있을수 있으므로, N:1관계인 JPA어노테이션 @Manytoone을 적용함.
*lazy와 eager전략을 아직 차이를 잘 모르겠음.

  ## 4.2 User
	*
	```
	@Entity//클래스가 mysql테이블이 자동생성.
	//@DynamicInsert//insert 할때 null값 -> default값 때문에
	public class User {
	@Id//primary key
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id; //auto_increment 
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public User()
	{
		
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public RoleType getRole() {
		return role;
	}


	public void setRole(RoleType role) {
		this.role = role;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Column(nullable = false,length = 30 ,unique = true)
	private String username;//아이디
	
	@Column(nullable = false,length = 100)//->hash로
	private String password;
	
	@Column(nullable = false,length = 50)
	private String email;
	
	//db는 roletype은 x
	@Enumerated(EnumType.STRING)
	private RoleType role; //enum으로 change // admin,user,manager
	
	
	@CreationTimestamp//time auto 
	private Timestamp createDate;
	
	}
	```
	
*유저 하나당 여러개의 게시글을 갖고있을 수 있으므로 

  ## 4.3 reply
	```
	*@Entity
	public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public Reply() {
		
	}

	public Reply(String content, Board board, User user) {
		this.content = content;
		this.board = board;
		this.user = user;
	}

	@Column(nullable = false,length = 300)
	private String content;
	
	@ManyToOne
	@JoinColumn(name="boardId")
	private Board board;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
	}
	```
	
  

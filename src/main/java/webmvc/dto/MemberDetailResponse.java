package webmvc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class MemberDetailResponse {
	private Long id;
	private String email;
//	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@JsonFormat(pattern = "yyyyMMddHHmmss")
	private LocalDateTime registerDateTime;
}

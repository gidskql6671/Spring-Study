package webmvc.dto;

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
	private LocalDateTime registerDateTime;
}

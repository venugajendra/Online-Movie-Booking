package com.mycode.show_service.dto;

import com.mycode.show_service.model.Show;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowWithScreenDTO {

    private Show show;
    private ScreenDTO screen;
}

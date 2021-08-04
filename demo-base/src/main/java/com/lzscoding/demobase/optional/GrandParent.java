package com.lzscoding.demobase.optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@ToString(callSuper = true)
@NoArgsConstructor
@Accessors(chain = true)
public class GrandParent {
    private Parent parent;
}
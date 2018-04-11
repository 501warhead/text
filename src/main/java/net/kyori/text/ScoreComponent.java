/*
 * This file is part of text, licensed under the MIT License.
 *
 * Copyright (c) 2017-2018 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.kyori.text;

import com.google.common.base.Objects;
import net.kyori.blizzard.NonNull;
import net.kyori.blizzard.Nullable;
import net.kyori.text.event.ClickEvent;
import net.kyori.text.event.HoverEvent;
import net.kyori.text.format.TextColor;
import net.kyori.text.format.TextDecoration;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * A scoreboard score component.
 */
public class ScoreComponent extends AbstractBuildableComponent<ScoreComponent, ScoreComponent.Builder> {
  /**
   * The score name.
   */
  @NonNull private final String name;
  /**
   * The score objective.
   */
  @NonNull private final String objective;
  /**
   * The value.
   */
  @Nullable private final String value;

  /**
   * Creates a score component builder.
   *
   * @return a builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Creates a score component builder with a name and objective.
   *
   * @param name the score name
   * @param objective the score objective
   * @return a builder
   */
  public static Builder builder(@NonNull final String name, @NonNull final String objective) {
    return new Builder().name(name).objective(objective);
  }

  /**
   * Creates a score component with a name and objective.
   *
   * @param name the score name
   * @param objective the score objective
   * @return the text component
   */
  public static ScoreComponent of(@NonNull final String name, @NonNull final String objective) {
    return of(name, objective, null);
  }

  /**
   * Creates a score component with a name, objective, and optional value.
   *
   * @param name the score name
   * @param objective the score objective
   * @param value the value
   * @return the text component
   */
  public static ScoreComponent of(@NonNull final String name, @NonNull final String objective, @Nullable final String value) {
    return builder()
      .name(name)
      .objective(objective)
      .value(value)
      .build();
  }

  protected ScoreComponent(@NonNull final Builder builder) {
    super(builder);
    this.name = builder.name;
    this.objective = builder.objective;
    this.value = builder.value;
  }

  protected ScoreComponent(@NonNull final List<Component> children, @Nullable final TextColor color, @NonNull final TextDecoration.State obfuscated, @NonNull final TextDecoration.State bold, @NonNull final TextDecoration.State strikethrough, @NonNull final TextDecoration.State underlined, @NonNull final TextDecoration.State italic, @Nullable final ClickEvent clickEvent, @Nullable final HoverEvent hoverEvent, @Nullable final String insertion, @NonNull final String name, @NonNull final String objective, @Nullable final String value) {
    super(children, color, obfuscated, bold, strikethrough, underlined, italic, clickEvent, hoverEvent, insertion);
    this.name = name;
    this.objective = objective;
    this.value = value;
  }

  /**
   * Gets the score name.
   *
   * @return the score name
   */
  @NonNull
  public String name() {
    return this.name;
  }

  /**
   * Sets the score name.
   *
   * @param name the score name
   * @return a copy of this component
   */
  @NonNull
  public ScoreComponent name(@NonNull final String name) {
    return new ScoreComponent(this.children, this.color, this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, this.clickEvent, this.hoverEvent, this.insertion, checkNotNull(name, "name"), this.objective, this.value);
  }

  /**
   * Gets the objective name.
   *
   * @return the objective name
   */
  @NonNull
  public String objective() {
    return this.objective;
  }

  /**
   * Sets the score objective.
   *
   * @param objective the score objective
   * @return a copy of this component
   */
  @NonNull
  public ScoreComponent objective(@NonNull final String objective) {
    return new ScoreComponent(this.children, this.color, this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, this.clickEvent, this.hoverEvent, this.insertion, this.name, checkNotNull(objective, "objective"), this.value);
  }

  /**
   * Gets the value.
   *
   * @return the value
   */
  @Nullable
  public String value() {
    return this.value;
  }

  /**
   * Sets the value.
   *
   * @param value the value
   * @return a copy of this component
   */
  @NonNull
  public ScoreComponent content(@NonNull final String value) {
    return new ScoreComponent(this.children, this.color, this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, this.clickEvent, this.hoverEvent, this.insertion, this.name, this.objective, value);
  }

  @NonNull
  @Override
  public ScoreComponent append(@NonNull final Component component) {
    this.detectCycle(component); // detect cycle before modifying
    final List<Component> children = new ArrayList<>(this.children.size() + 1);
    children.addAll(this.children);
    children.add(component);
    return new ScoreComponent(children, this.color, this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, this.clickEvent, this.hoverEvent, this.insertion, this.name, this.objective, this.value);
  }

  @NonNull
  @Override
  public ScoreComponent color(@Nullable final TextColor color) {
    return new ScoreComponent(this.children, color, this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, this.clickEvent, this.hoverEvent, this.insertion, this.name, this.objective, this.value);
  }

  @NonNull
  @Override
  public ScoreComponent decoration(@NonNull final TextDecoration decoration, final boolean flag) {
    return (ScoreComponent) super.decoration(decoration, flag);
  }

  @NonNull
  @Override
  public ScoreComponent decoration(@NonNull final TextDecoration decoration, @NonNull final TextDecoration.State state) {
    switch(decoration) {
      case BOLD: return new ScoreComponent(this.children, this.color, this.obfuscated, checkNotNull(state, "flag"), this.strikethrough, this.underlined, this.italic, this.clickEvent, this.hoverEvent, this.insertion, this.name, this.objective, this.value);
      case ITALIC: return new ScoreComponent(this.children, this.color, this.obfuscated, this.bold, this.strikethrough, this.underlined, checkNotNull(state, "flag"), this.clickEvent, this.hoverEvent, this.insertion, this.name, this.objective, this.value);
      case UNDERLINE: return new ScoreComponent(this.children, this.color, this.obfuscated, this.bold, this.strikethrough, checkNotNull(state, "flag"), this.italic, this.clickEvent, this.hoverEvent, this.insertion, this.name, this.objective, this.value);
      case STRIKETHROUGH: return new ScoreComponent(this.children, this.color, this.obfuscated, this.bold, checkNotNull(state, "flag"), this.underlined, this.italic, this.clickEvent, this.hoverEvent, this.insertion, this.name, this.objective, this.value);
      case OBFUSCATED: return new ScoreComponent(this.children, this.color, checkNotNull(state, "flag"), this.bold, this.strikethrough, this.underlined, this.italic, this.clickEvent, this.hoverEvent, this.insertion, this.name, this.objective, this.value);
      default: throw new IllegalArgumentException(String.format("unknown decoration '%s'", decoration));
    }
  }

  @NonNull
  @Override
  public ScoreComponent clickEvent(@Nullable final ClickEvent event) {
    return new ScoreComponent(this.children, this.color, this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, event, this.hoverEvent, this.insertion, this.name, this.objective, this.value);
  }

  @NonNull
  @Override
  public ScoreComponent hoverEvent(@Nullable final HoverEvent event) {
    if(event != null) this.detectCycle(event.value()); // detect cycle before modifying
    return new ScoreComponent(this.children, this.color, this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, this.clickEvent, event, this.insertion, this.name, this.objective, this.value);
  }

  @NonNull
  @Override
  public ScoreComponent insertion(@Nullable final String insertion) {
    return new ScoreComponent(this.children, this.color, this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, this.clickEvent, this.hoverEvent, insertion, this.name, this.objective, this.value);
  }

  @NonNull
  @Override
  public ScoreComponent mergeStyle(@NonNull final Component that) {
    return new ScoreComponent(this.children, that.color(), that.decoration(TextDecoration.OBFUSCATED), that.decoration(TextDecoration.BOLD), that.decoration(TextDecoration.STRIKETHROUGH), that.decoration(TextDecoration.UNDERLINE), that.decoration(TextDecoration.ITALIC), that.clickEvent(), that.hoverEvent(), that.insertion(), this.name, this.objective, this.value);
  }

  @NonNull
  @Override
  public ScoreComponent mergeColor(@NonNull final Component that) {
    return new ScoreComponent(this.children, that.color(), this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, this.clickEvent, this.hoverEvent, this.insertion, this.name, this.objective, this.value);
  }

  @NonNull
  @Override
  public ScoreComponent mergeDecorations(@NonNull final Component that) {
    final TextDecoration.State obfuscated = that.decoration(TextDecoration.OBFUSCATED) != TextDecoration.State.NOT_SET ? that.decoration(TextDecoration.OBFUSCATED) : this.obfuscated;
    final TextDecoration.State bold = that.decoration(TextDecoration.BOLD) != TextDecoration.State.NOT_SET ? that.decoration(TextDecoration.BOLD) : this.bold;
    final TextDecoration.State strikethrough = that.decoration(TextDecoration.STRIKETHROUGH) != TextDecoration.State.NOT_SET ? that.decoration(TextDecoration.STRIKETHROUGH) : this.strikethrough;
    final TextDecoration.State underlined = that.decoration(TextDecoration.UNDERLINE) != TextDecoration.State.NOT_SET ? that.decoration(TextDecoration.UNDERLINE) : this.underlined;
    final TextDecoration.State italic = that.decoration(TextDecoration.ITALIC) != TextDecoration.State.NOT_SET ? that.decoration(TextDecoration.ITALIC) : this.italic;
    return new ScoreComponent(this.children, this.color, obfuscated, bold, strikethrough, underlined, italic, this.clickEvent, this.hoverEvent, this.insertion, this.name, this.objective, this.value);
  }

  @NonNull
  @Override
  public ScoreComponent mergeEvents(@NonNull final Component that) {
    return new ScoreComponent(this.children, this.color, this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, that.clickEvent(), that.hoverEvent(), this.insertion, this.name, this.objective, this.value);
  }

  @NonNull
  @Override
  public ScoreComponent resetStyle() {
    return new ScoreComponent(this.children, null, TextDecoration.State.NOT_SET, TextDecoration.State.NOT_SET, TextDecoration.State.NOT_SET, TextDecoration.State.NOT_SET, TextDecoration.State.NOT_SET, null, null, null, this.name, this.objective, this.value);
  }

  @NonNull
  @Override
  public ScoreComponent copy() {
    return new ScoreComponent(this.children, this.color, this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, this.clickEvent, this.hoverEvent, this.insertion, this.name, this.objective, this.value);
  }

  @Override
  public boolean equals(@Nullable final Object other) {
    if(this == other) return true;
    if(other == null || !(other instanceof ScoreComponent)) return false;
    if(!super.equals(other)) return false;
    final ScoreComponent that = (ScoreComponent) other;
    return Objects.equal(this.name, that.name) && Objects.equal(this.objective, that.objective) && Objects.equal(this.value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode(), this.name, this.objective, this.value);
  }

  @Override
  protected void populateToString(@NonNull final Objects.ToStringHelper builder) {
    builder
      .add("name", this.name)
      .add("objective", this.objective)
      .add("value", this.value);
  }

  @NonNull
  @Override
  public Builder toBuilder() {
    return new Builder(this);
  }

  /**
   * A score component builder.
   */
  public static class Builder extends AbstractBuildableComponent.AbstractBuilder<ScoreComponent, Builder> {
    @Nullable private String name;
    @Nullable private String objective;
    @Nullable private String value;

    Builder() {
    }

    Builder(@NonNull final ScoreComponent component) {
      super(component);
      this.name = component.name();
      this.objective = component.objective();
      this.value = component.value();
    }

    /**
     * Sets the score name.
     *
     * @param name the score name
     * @return this builder
     */
    @NonNull
    public Builder name(@NonNull final String name) {
      this.name = name;
      return this;
    }

    /**
     * Sets the score objective.
     *
     * @param objective the score objective
     * @return this builder
     */
    @NonNull
    public Builder objective(@NonNull final String objective) {
      this.objective = objective;
      return this;
    }

    /**
     * Sets the value.
     *
     * @param value the value
     * @return this builder
     */
    @NonNull
    public Builder value(@Nullable final String value) {
      this.value = value;
      return this;
    }

    @NonNull
    @Override
    public ScoreComponent build() {
      checkState(this.name != null, "name must be set");
      checkState(this.objective != null, "objective must be set");
      return new ScoreComponent(this);
    }
  }
}

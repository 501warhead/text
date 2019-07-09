/*
 * This file is part of text, licensed under the MIT License.
 *
 * Copyright (c) 2017-2019 KyoriPowered
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

import net.kyori.text.format.Style;
import net.kyori.text.format.TextColor;
import net.kyori.text.format.TextDecoration;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * A translatable text component.
 */
public interface TranslatableComponent extends BuildableComponent<TranslatableComponent, TranslatableComponent.Builder>, ScopedComponent<TranslatableComponent> {
  /**
   * Creates a translatable component builder.
   *
   * @return a builder
   */
  static @NonNull Builder builder() {
    return new TranslatableComponentImpl.BuilderImpl();
  }

  /**
   * Creates a translatable component builder with a translation key.
   *
   * @param key the translation key
   * @return a builder
   */
  static @NonNull Builder builder(final @NonNull String key) {
    return builder().key(key);
  }

  /**
   * Creates a translatable component with a translation key.
   *
   * @param key the translation key
   * @return the translatable component
   */
  static @NonNull TranslatableComponent of(final @NonNull String key) {
    return builder(key).build();
  }

  /**
   * Creates a translatable component with a translation key, and optional color.
   *
   * @param key the translation key
   * @param color the color
   * @return the translatable component
   */
  static @NonNull TranslatableComponent of(final @NonNull String key, final @Nullable TextColor color) {
    return builder(key).color(color).build();
  }

  /**
   * Creates a translatable component with a translation key, and optional color and decorations.
   *
   * @param key the translation key
   * @param color the color
   * @param decorations the decorations
   * @return the text component
   */
  static @NonNull TranslatableComponent of(final @NonNull String key, final @Nullable TextColor color, final TextDecoration @NonNull ... decorations) {
    final Set<TextDecoration> activeDecorations = new HashSet<>(decorations.length);
    Collections.addAll(activeDecorations, decorations);
    return of(key, color, activeDecorations);
  }

  /**
   * Creates a translatable component with a translation key, and optional color and decorations.
   *
   * @param key the translation key
   * @param color the color
   * @param decorations the decorations
   * @return the translatable component
   */
  static @NonNull TranslatableComponent of(final @NonNull String key, final @Nullable TextColor color, final @NonNull Set<TextDecoration> decorations) {
    return builder(key).color(color).decorations(decorations, true).build();
  }

  /**
   * Creates a translatable component with a translation key and optional style.
   *
   * @param key the translation key
   * @param style the style
   * @return the translatable component
   */
  static @NonNull TranslatableComponent of(final @NonNull String key, final @NonNull Style style) {
    return builder(key).style(style).build();
  }

  /**
   * Creates a translatable component with a translation key and arguments.
   *
   * @param key the translation key
   * @param args the translation arguments
   * @return the translatable component
   */
  static @NonNull TranslatableComponent of(final @NonNull String key, final @NonNull Component... args) {
    return of(key, (TextColor) null, args);
  }

  /**
   * Creates a translatable component with a translation key, arguments, and optional color.
   *
   * @param key the translation key
   * @param color the color
   * @param args the translation arguments
   * @return the translatable component
   */
  static @NonNull TranslatableComponent of(final @NonNull String key, final @Nullable TextColor color, final @NonNull Component... args) {
    return of(key, color, Collections.emptySet(), args);
  }

  /**
   * Creates a translatable component with a translation key, arguments, and optional color and decorations.
   *
   * @param key the translation key
   * @param color the color
   * @param decorations the decorations
   * @param args the translation arguments
   * @return the translatable component
   */
  static @NonNull TranslatableComponent of(final @NonNull String key, final @Nullable TextColor color, final @NonNull Set<TextDecoration> decorations, final @NonNull Component... args) {
    return of(key, color, decorations, Arrays.asList(args));
  }

  /**
   * Creates a translatable component with a translation key, arguments, and optional color and decorations.
   *
   * @param key the translation key
   * @param style the style
   * @param args the translation arguments
   * @return the translatable component
   */
  static @NonNull TranslatableComponent of(final @NonNull String key, final @NonNull Style style, final @NonNull Component... args) {
    return builder(key).style(style).args(Arrays.asList(args)).build();
  }

  /**
   * Creates a translatable component with a translation key and arguments.
   *
   * @param key the translation key
   * @param args the translation arguments
   * @return the translatable component
   */
  static @NonNull TranslatableComponent of(final @NonNull String key, final @NonNull List<? extends Component> args) {
    return of(key, null, args);
  }

  /**
   * Creates a translatable component with a translation key, arguments, and optional color.
   *
   * @param key the translation key
   * @param color the color
   * @param args the translation arguments
   * @return the translatable component
   */
  static TranslatableComponent of(final @NonNull String key, final @Nullable TextColor color, final @NonNull List<? extends Component> args) {
    return of(key, color, Collections.emptySet(), args);
  }

  /**
   * Creates a translatable component with a translation key, arguments, and optional color and decorations.
   *
   * @param key the translation key
   * @param color the color
   * @param decorations the decorations
   * @param args the translation arguments
   * @return the translatable component
   */
  static @NonNull TranslatableComponent of(final @NonNull String key, final @Nullable TextColor color, final @NonNull Set<TextDecoration> decorations, final @NonNull List<? extends Component> args) {
    return builder(key).color(color).decorations(decorations, true).args(args).build();
  }

  /**
   * Creates a translatable component by applying configuration from {@code consumer}.
   *
   * @param consumer the builder configurator
   * @return the translatable component
   */
  static @NonNull TranslatableComponent make(final @NonNull Consumer<? super Builder> consumer) {
    final Builder builder = builder();
    consumer.accept(builder);
    return builder.build();
  }

  /**
   * Creates a translatable component by applying configuration from {@code consumer}.
   *
   * @param key the translation key
   * @param consumer the builder configurator
   * @return the translatable component
   */
  static @NonNull TranslatableComponent make(final @NonNull String key, final @NonNull Consumer<? super Builder> consumer) {
    final Builder builder = builder(key);
    consumer.accept(builder);
    return builder.build();
  }

  /**
   * Creates a translatable component by applying configuration from {@code consumer}.
   *
   * @param key the translation key
   * @param args the translation arguments
   * @param consumer the builder configurator
   * @return the translatable component
   */
  static @NonNull TranslatableComponent make(final @NonNull String key, final @NonNull List<? extends Component> args, final @NonNull Consumer<? super Builder> consumer) {
    final Builder builder = builder(key).args(args);
    consumer.accept(builder);
    return builder.build();
  }

  /**
   * Gets the translation key.
   *
   * @return the translation key
   */
  @NonNull String key();

  /**
   * Sets the translation key.
   *
   * @param key the translation key
   * @return a copy of this component
   */
  @NonNull TranslatableComponent key(final @NonNull String key);

  /**
   * Gets the unmodifiable list of translation arguments.
   *
   * @return the unmodifiable list of translation arguments
   */
  @NonNull List<Component> args();

  /**
   * Sets the translation arguments for this component.
   *
   * @param args the translation arguments
   * @return this component
   */
  @NonNull TranslatableComponent args(final @NonNull Component@NonNull... args);

  /**
   * Sets the translation arguments for this component.
   *
   * @param args the translation arguments
   * @return this component
   */
  @NonNull TranslatableComponent args(final @NonNull List<? extends Component> args);

  /**
   * A text component builder.
   */
  interface Builder extends ComponentBuilder<TranslatableComponent, Builder> {
    /**
     * Sets the translation key.
     *
     * @param key the translation key
     * @return this builder
     */
    @NonNull Builder key(final @NonNull String key);

    /**
     * Sets the translation args.
     *
     * @param args the translation args
     * @return this builder
     */
    @NonNull Builder args(final @NonNull ComponentBuilder<?, ?>... args);

    /**
     * Sets the translation args.
     *
     * @param args the translation args
     * @return this builder
     */
    @NonNull Builder args(final @NonNull Component... args);

    /**
     * Sets the translation args.
     *
     * @param args the translation args
     * @return this builder
     */
    @NonNull Builder args(final @NonNull List<? extends Component> args);
  }
}
